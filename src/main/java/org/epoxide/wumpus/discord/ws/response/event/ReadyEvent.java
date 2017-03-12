package org.epoxide.wumpus.discord.ws.response.event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.Guild;
import org.epoxide.wumpus.discord.User;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.DiscordEvent;

import java.util.List;

@DiscordEvent(name = "READY", raw = true)
public class ReadyEvent extends Event {

    @Expose
    @SerializedName("v")
    private int version;

    @Expose
    @SerializedName("user")
    private User self;

    @Expose
    private String[] shard;
    @Expose
    private String session_id;
    @Expose
    private List<Guild> guilds;

    @Expose
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
