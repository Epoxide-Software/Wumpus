package org.epoxide.wumpus.discord.ws.response.event;

import com.google.gson.annotations.SerializedName;
import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.type.Guild;
import org.epoxide.wumpus.discord.type.User;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.DiscordEvent;

import java.util.List;

@DiscordEvent(name = "READY", raw = true)
public class ReadyEvent extends Event {

    @SerializedName("v")
    private int version;
    @SerializedName("user")
    private User self;
    private String[] shard;
    private String session_id;
    private List<Guild> guilds;
    @SerializedName("_trace")
    private String[] trace;

    @Override
    public void onCall(Wumpus wumpus, EventWebSocket ws, Packet response) {
        wumpus.setSelf(self);
        System.out.println("It is ready");
    }

    public int getVersion() {
        return version;
    }

    public User getSelf() {
        return self;
    }

    public String[] getShard() {
        return shard;
    }

    public String getSession_id() {
        return session_id;
    }

    public List<Guild> getGuilds() {
        return guilds;
    }

    public String[] getTrace() {
        return trace;
    }
}
