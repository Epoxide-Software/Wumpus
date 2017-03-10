package org.epoxide.wumpus.discord;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.adapter.PacketDeserializer;
import org.epoxide.wumpus.discord.ws.response.Data;

public class EventWebSocket extends WebSocketAdapter {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Packet.class, new PacketDeserializer())
            .create();

    private final Wumpus wumpus;

    public long seq;

    public EventWebSocket(Wumpus wumpus) {
        this.wumpus = wumpus;
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
        Packet packet = GSON.fromJson(message, Packet.class);
        System.out.println(message);
        if (packet.getData() != null)
            ((Data) packet.getData()).onCall(this.wumpus, this, packet);
    }
}
