package org.epoxide.wumpus.discord.type;

import com.google.gson.annotations.SerializedName;

public class Channel {
    private final String id;
    private String name;
    private String type;
    private int position;
    @SerializedName("is_private")
    private boolean isPrivate;

    public Channel(String id) {

        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
