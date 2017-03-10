package org.epoxide.wumpus.discord;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.ws.Packet;

import java.io.IOException;

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


    public long seq;
    public String token;

    public EventWebSocket() {
        //TODO Move to instance based not Wumpus
        this.token = Wumpus.getInstance().getToken();
    }

    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);
        System.out.println("Websocket Connected.");
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        System.out.println("Websocket Disconnected. " + statusCode + ":" + reason);
        super.onWebSocketClose(statusCode, reason);
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        System.out.println("Encountered websocket error: " + cause);
    }

    @Override
    public void onWebSocketText(String message) {
        try {
            Packet packet = JACKSON.readValue(message, Packet.class);
            System.out.println(message);
            if (packet.getData() != null)
                packet.getData().onCall(Wumpus.getInstance(), this, packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
