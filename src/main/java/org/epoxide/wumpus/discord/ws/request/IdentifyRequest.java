package org.epoxide.wumpus.discord.ws.request;

import com.google.gson.annotations.SerializedName;
import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.response.Data;

public class IdentifyRequest implements Data {
    private String token;
    private Properties properties;
    private boolean compress;
    @SerializedName("large_threshold")
    private Integer largeThreshold;
    private int[] shard;

    public IdentifyRequest(String token, Properties properties, boolean compress, Integer largeThreshold, int[] shard) {
        this.token = token;
        this.properties = properties;
        this.compress = compress;
        this.largeThreshold = largeThreshold;
        this.shard = shard;
    }

    public static class Properties {
        @SerializedName("$os")
        String os;
        @SerializedName("$device")
        String device;
        @SerializedName("$browser")
        String browser;
        @SerializedName("$referrer")
        String referrer;
        @SerializedName("$referring_domain")
        String referringDomain;

        public Properties(String os, String device, String browser, String referrer, String referringDomain) {
            this.os = os;
            this.device = device;
            this.browser = browser;
            this.referrer = referrer;
            this.referringDomain = referringDomain;
        }
    }
}
