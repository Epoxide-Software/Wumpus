package org.epoxide.wumpus.discord.ws.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.epoxide.wumpus.discord.ws.response.Data;

public class IdentifyRequest implements Data {
    @Expose
    private String token;
    @Expose
    private Properties properties;
    @Expose
    private boolean compress;
    @Expose
    @SerializedName("large_threshold")
    private Integer largeThreshold;
    @Expose
    private int[] shard;

    public IdentifyRequest(String token, Properties properties, boolean compress, Integer largeThreshold, int[] shard) {
        this.token = token;
        this.properties = properties;
        this.compress = compress;
        this.largeThreshold = largeThreshold;
        this.shard = shard;
    }

    public static class Properties {
        @Expose
        @SerializedName("$os")
        private String os;
        @Expose
        @SerializedName("$device")
        private String device;
        @Expose
        @SerializedName("$browser")
        private String browser;
        @Expose
        @SerializedName("$referrer")
        private String referrer;
        @Expose
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
