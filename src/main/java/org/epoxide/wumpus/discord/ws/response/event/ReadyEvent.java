package org.epoxide.wumpus.discord.ws.response.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.DiscordEvent;

@DiscordEvent(name = "READY")
public class ReadyEvent extends Event {

    @JsonProperty("v")
    private int version;

    public int getVersion() {
        return version;
    }

    @Override
    public void onCall(Packet response, EventWebSocket webSocket) {
        System.out.println("It is ready");
    }
}
