package org.epoxide.wumpus.discord.ws.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.response.Data;

public class IdentifyRequest implements Data {
    private String token;
    private Properties properties;
    private boolean compress;
    @JsonProperty("large_threshold")
    private Integer largeThreshold;
    private int[] shard;

    public IdentifyRequest(String token, Properties properties, boolean compress, Integer largeThreshold, int[] shard) {
        this.token = token;
        this.properties = properties;
        this.compress = compress;
        this.largeThreshold = largeThreshold;
        this.shard = shard;
    }

    @Override
    public void onCall(Packet response, EventWebSocket webSocket) {

    }

    public static class Properties {
        @JsonProperty("$os")
        String os;
        @JsonProperty("$device")
        String device;
        @JsonProperty("$browser")
        String browser;
        @JsonProperty("$referrer")
        String referrer;
        @JsonProperty("$referring_domain")
        String referring_domain;

        public Properties(String os, String device, String browser, String referrer, String referring_domain) {
            this.os = os;
            this.device = device;
            this.browser = browser;
            this.referrer = referrer;
            this.referring_domain = referring_domain;
        }
    }
}
