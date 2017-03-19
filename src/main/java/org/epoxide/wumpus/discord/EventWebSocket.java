package org.epoxide.wumpus.discord;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.type.Channel;
import org.epoxide.wumpus.discord.type.Guild;
import org.epoxide.wumpus.discord.type.User;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.adapter.ChannelDeserializer;
import org.epoxide.wumpus.discord.ws.adapter.GuildDeserializer;
import org.epoxide.wumpus.discord.ws.adapter.PacketDeserializer;
import org.epoxide.wumpus.discord.ws.adapter.UserDeserializer;
import org.epoxide.wumpus.discord.ws.response.Data;
import org.epoxide.wumpus.discord.ws.response.HelloResponse;

import java.util.logging.Level;

public class EventWebSocket extends WebSocketAdapter {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Packet.class, new PacketDeserializer())
            .registerTypeAdapter(User.class, new UserDeserializer())
            .registerTypeAdapter(Guild.class, new GuildDeserializer())
            .registerTypeAdapter(Channel.class, new ChannelDeserializer())
            .create();

    private final Wumpus wumpus;

    public long seq;

    public EventWebSocket(Wumpus wumpus) {
        this.wumpus = wumpus;
    }

    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);
        Wumpus.LOG.log(Level.SEVERE, "WebSocket Connected.");
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        super.onWebSocketClose(statusCode, reason);
        Wumpus.LOG.log(Level.SEVERE, "WebSocket Disconnected. " + statusCode + ":" + reason);
        HelloResponse.keepAlive.shutdown();
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        Wumpus.LOG.log(Level.SEVERE, "Encountered WebSocket error: " + cause);
    }

    @Override
    public void onWebSocketText(String message) {

        System.out.println(message);
        Packet packet = GSON.fromJson(message, Packet.class);
        if (packet.getObject() != null && packet.getObject() instanceof Data) {
            ((Data) packet.getObject()).onCall(this.wumpus, this, packet);
        }
    }
}
