package org.epoxide.wumpus.discord;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.EventFactory;
import org.epoxide.wumpus.factory.FactoryBus;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.channels.UnresolvedAddressException;
import java.util.stream.Collectors;
import java.util.zip.InflaterInputStream;

public class EventWebSocket extends WebSocketAdapter {
    public static final ObjectMapper JACKSON = new ObjectMapper()
            .enable(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID)
            .enable(SerializationFeature.WRITE_NULL_MAP_VALUES)
            .enable(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(JsonParser.Feature.ALLOW_COMMENTS)
            .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
            .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
            .enable(JsonParser.Feature.ALLOW_MISSING_VALUES)
            .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    private final WebSocketClient ws;
    private final Wumpus wumpus;

    public long seq;
    public String token;

    public EventWebSocket(Wumpus wumpus) {
        this.wumpus = wumpus;
        this.ws = new WebSocketClient(new SslContextFactory());
        this.token = this.wumpus.getToken();

        try {
            this.ws.setDaemon(true);
            this.ws.getPolicy().setMaxBinaryMessageSize(Integer.MAX_VALUE);
            this.ws.getPolicy().setMaxTextMessageSize(Integer.MAX_VALUE);
            this.ws.start();
            this.ws.connect(this, new URI(Wumpus.ENDPOINTS.getGateway().execute().body().url + "?v=5&encoding=json"), new ClientUpgradeRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWebSocketConnect(Session sess) {
        System.out.println("Websocket Connected.");
        FactoryBus.registerFactory(new EventFactory());
        super.onWebSocketConnect(sess);
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        super.onWebSocketClose(statusCode, reason);
        System.out.println("Websocket Disconnected." + reason + ":" + statusCode);
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        super.onWebSocketError(cause);
        if (cause instanceof UnresolvedAddressException) {
            System.out.println("Caught UnresolvedAddressException. Internet outage?");
        } else {
            System.out.println("Encountered websocket error: " + cause);
        }

    }

    @Override
    public void onWebSocketBinary(byte[] payload, int offset, int len) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new InflaterInputStream(new ByteArrayInputStream(payload))));
        String message = reader.lines().collect(Collectors.joining());
        this.onWebSocketText(message);
    }

    @Override
    public void onWebSocketText(String message) {
        //TODO Dejson it
        try {
            Packet packet = JACKSON.readValue(message, Packet.class);
            System.out.println(packet);
            if (packet.getData() != null)
                packet.getData().onCall(packet, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Data data = GSON.fromJson(message, Data.class);
//        if (data != null)
//            data.onCall(response,this);
//        switch (response.getOp()) {
//            case DISPATCH: {
//                Event event = gson.fromJson(message, Event.class);
//                System.out.println(event);
//                break;
//            }
//            case HELLO: {
//                Data data = GatewayFactory.getInstance(response.getOp(),message,gson,Data.class);
//
//                HelloResponse r = (HelloResponse) data;
//
//                break;
//            }
//            case HEARTBEAT_ACK: {
//                System.out.println("ACK");
//                //TODO This is a ping confirm, tell the websocket not to reconnect next heartbeat packet
//            }
//            default: {
//
//                break;
//            }
//        }

    }
}
