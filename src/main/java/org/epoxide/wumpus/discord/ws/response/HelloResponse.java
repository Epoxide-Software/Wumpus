package org.epoxide.wumpus.discord.ws.response;

import com.google.gson.annotations.SerializedName;
import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.DiscordGateway;
import org.epoxide.wumpus.discord.ws.request.IdentifyRequest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@DiscordGateway(opcode = 10)
public class HelloResponse implements Data {

    private Runnable heartbeatTask;
    public static ScheduledExecutorService keepAlive = Executors.newSingleThreadScheduledExecutor();

    @SerializedName("_trace")
    private String[] trace;
    @SerializedName("heartbeat_interval")
    private int heartbeatInterval;

    public long getHeartbeatInterval() {
        return this.heartbeatInterval;
    }

    @Override
    public void onCall(Wumpus wumpus, EventWebSocket ws, Packet response) {
        heartbeatTask = () -> {
            if (ws.getSession() != null) {
                ws.getSession().getRemote().sendStringByFuture(EventWebSocket.GSON.toJson(new Packet(1, ws.seq)));
            }
        };

        keepAlive.scheduleAtFixedRate(heartbeatTask, 0, this.getHeartbeatInterval(), TimeUnit.MILLISECONDS);

        IdentifyRequest.Properties properties = new IdentifyRequest.Properties(System.getProperty("os.name"), "Wumpus", "Wumpus", "", "");
        IdentifyRequest request = new IdentifyRequest("Bot " + wumpus.getToken(), properties, false, 0, new int[]{0, 1});
        ws.getSession().getRemote().sendStringByFuture(EventWebSocket.GSON.toJson(new Packet(2, request)));
    }
}
