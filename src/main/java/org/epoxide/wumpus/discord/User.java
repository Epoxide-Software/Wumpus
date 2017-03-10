package org.epoxide.wumpus.discord;

public class User {
    private String username;
    private String id;
    private String discriminator;
    private boolean bot;
    private String avatar;

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
