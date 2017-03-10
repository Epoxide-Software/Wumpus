package org.epoxide.wumpus.discord.ws.response.event;

import com.google.gson.annotations.SerializedName;
import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.User;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.DiscordEvent;

@DiscordEvent(name = "READY")
public class ReadyEvent extends Event {

    @SerializedName("v")
    private int version;

    @SerializedName("user")
    private User self;

    private String[] shard;
    private String session_id;
//    private Guild[] guilds;

    @SerializedName("_trace")
    private String[] trace;

    public int getVersion() {
        return version;
    }

    @Override
    public void onCall(Wumpus wumpus, EventWebSocket ws, Packet response) {
        wumpus.setSelf(self);
        System.out.println("It is ready");

    }
}
