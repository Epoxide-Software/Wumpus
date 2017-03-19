package org.epoxide.wumpus.discord.type;

import java.util.List;

public class User {
    private final String id;

    private String username;
    private String discriminator;
    private boolean bot;
    private String avatar;

    private List<Guild> guilds;

    public User(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setGuilds(List<Guild> guilds) {
        this.guilds = guilds;
    }

    public String getUsername() {
        return this.username;
    }

    public String getId() {
        return this.id;
    }

    public String getDiscriminator() {
        return this.discriminator;
    }

    public boolean isBot() {
        return this.bot;
    }

    public String getAvatar() {
        return this.avatar;
    }
}
