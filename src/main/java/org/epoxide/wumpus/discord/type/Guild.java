package org.epoxide.wumpus.discord.type;

import com.google.gson.annotations.SerializedName;
import org.epoxide.wumpus.discord.ws.factory.DiscordEvent;

import java.util.List;
import java.util.Map;

@DiscordEvent(name = "GUILD_CREATE")
public class Guild {
    private final String id;
    private String name;
    private boolean unavailable;
    @SerializedName("verification_level")
    private int verificationLevel;
    private String region;
    private User owner;
    @SerializedName("mfa_level")
    private int mfaLevel;
    @SerializedName("member_count")
    private int memberCount;
    private boolean large;
    private String icon;
    @SerializedName("default_message_notifications")
    private int defaultMessageNotifications;
    @SerializedName("afk_timeout")
    private int afkTimeout;
    @SerializedName("afk_channel_id")
    private Channel afkChannel;

    private Map<String, Role> roles;
    //    private Presence[] presences;
    private List<User> members;
    //    private Feature[] features;
//    private Emoji[] emojis;
    private List<Channel> channels;

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

    public void setAfkChannel(Channel afkChannel) {
        this.afkChannel = afkChannel;
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

    public Channel getAfkChannel() {
        return afkChannel;
    }
}