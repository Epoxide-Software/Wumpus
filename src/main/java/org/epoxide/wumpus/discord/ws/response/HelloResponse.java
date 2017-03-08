package org.epoxide.wumpus.discord.ws.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.DiscordGateway;
import org.epoxide.wumpus.discord.ws.request.IdentifyRequest;
import org.epoxide.wumpus.discord.ws.request.RequestHeartbeat;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@DiscordGateway(opcode = 10)
public class HelloResponse implements Data {

    private Runnable heartbeatTask;
    private ScheduledExecutorService keepAlive = Executors.newSingleThreadScheduledExecutor();

    @JsonProperty("_trace")
    private String[] trace;
    @JsonProperty("heartbeat_interval")
    private long heartbeatInterval;

    public long getHeartbeatInterval() {
        return this.heartbeatInterval;
    }

    @Override
    public void onCall(Packet response, EventWebSocket webSocket) {
        heartbeatTask = () -> {
            if (webSocket.getSession() != null && webSocket.getSession().isOpen()) {
                try {
                    webSocket.getSession().getRemote().sendStringByFuture(EventWebSocket.JACKSON.writeValueAsString(new RequestHeartbeat(1, webSocket.seq)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        try {
            keepAlive.scheduleAtFixedRate(heartbeatTask, 0, this.getHeartbeatInterval(), TimeUnit.MILLISECONDS);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        IdentifyRequest.Properties properties = new IdentifyRequest.Properties(System.getProperty("os.name"), "Wumpus", "Wumpus", "", "");
        IdentifyRequest request = new IdentifyRequest("Bot "+webSocket.token, properties, false, 0, new int[]{0, 1});
        try {
            webSocket.getSession().getRemote().sendStringByFuture(EventWebSocket.JACKSON.writeValueAsString(new Packet(2, request)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
