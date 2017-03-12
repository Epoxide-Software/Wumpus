package org.epoxide.wumpus.discord;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.epoxide.wumpus.discord.ws.factory.DiscordEvent;

import java.util.List;

@DiscordEvent(name = "GUILD_CREATE")
public class Guild {
    @Expose
    private final String id;
    @Expose
    private String name;
    @Expose
    private boolean unavailable;
    @Expose
    @SerializedName("verification_level")
    private int verificationLevel;
    @Expose
    private String region;
    @Expose
    private User owner;
    @Expose
    @SerializedName("mfa_level")
    private int mfaLevel;
    private int memberCount;
    private boolean large;
    private String icon;
    private int defaultMessageNotifications;
    private int afkTimeout;
    private String afkChannelID;

    //    private Role[] roles;
//    private Presence[] presences;
    private List<User> members;
//    private Feature[] features;
//    private Emoji[] emojis;
//    private Channel channels;

    public Guild(String id) {
        this.id = id;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnavailable(boolean unavailable) {
        this.unavailable = unavailable;
    }

    public void setVerificationLevel(int verificationLevel) {
        this.verificationLevel = verificationLevel;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setMfaLevel(int mfaLevel) {
        this.mfaLevel = mfaLevel;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public void setLarge(boolean large) {
        this.large = large;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setDefaultMessageNotifications(int defaultMessageNotifications) {
        this.defaultMessageNotifications = defaultMessageNotifications;
    }

    public void setAfkTimeout(int afkTimeout) {
        this.afkTimeout = afkTimeout;
    }

    public void setAfkChannelID(String afkChannelID) {
        this.afkChannelID = afkChannelID;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isUnavailable() {
        return unavailable;
    }

    public int getVerificationLevel() {
        return verificationLevel;
    }

    public String getRegion() {
        return region;
    }

    public User getOwner() {
        return owner;
    }

    public int getMfaLevel() {
        return mfaLevel;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public boolean isLarge() {
        return large;
    }

    public String getIcon() {
        return icon;
    }

    public int getDefaultMessageNotifications() {
        return defaultMessageNotifications;
    }

    public int getAfkTimeout() {
        return afkTimeout;
    }

    public String getAfkChannelID() {
        return afkChannelID;
    }
}