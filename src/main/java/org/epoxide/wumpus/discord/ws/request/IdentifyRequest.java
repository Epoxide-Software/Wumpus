package org.epoxide.wumpus.discord.ws.request;

import com.google.gson.annotations.SerializedName;
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
        private String os;
        @SerializedName("$device")
        private String device;
        @SerializedName("$browser")
        private String browser;
        @SerializedName("$referrer")
        private String referrer;
        @SerializedName("$referring_domain")
        private String referringDomain;

        public Properties(String os, String device, String browser, String referrer, String referringDomain) {
            this.os = os;
            this.device = device;
            this.browser = browser;
            this.referrer = referrer;
            this.referringDomain = referringDomain;
        }
    }
}
