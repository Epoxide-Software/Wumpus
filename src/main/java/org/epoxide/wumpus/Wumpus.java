package org.epoxide.wumpus;

import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.epoxide.annotationfactory.FactoryBus;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.type.User;
import org.epoxide.wumpus.utils.Endpoints;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Wumpus {

    public static final Logger LOG = Logger.getLogger("Wumpus");

    public static final Endpoints ENDPOINTS = new Retrofit.Builder()
            .baseUrl("https://discordapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Endpoints.class);

    private final String token;
    private final WebSocketClient ws;
    private final EventWebSocket websocket;
    private User self;

    public Wumpus(String token, int[] shards, int shardCount) {
        //TODO if shards and shardCount is null and -1 get recommended from /gateway/bot
        this.token = token;

        //TODO move to shard
        this.websocket = new EventWebSocket(this);
        this.ws = new WebSocketClient(new SslContextFactory());
        try {
            this.ws.setDaemon(true);
            this.ws.getPolicy().setMaxBinaryMessageSize(Integer.MAX_VALUE);
            this.ws.getPolicy().setMaxTextMessageSize(Integer.MAX_VALUE);
            this.ws.start();
            this.ws.connect(this.websocket, new URI(Wumpus.ENDPOINTS.getGateway().execute().body().url + "?v=5&encoding=json"), new ClientUpgradeRequest());
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "WebSocket Internal Error");
            return;
        }

        FactoryBus.autoSearch();
    }

    public String getToken() {
        return token;
    }

    public void setSelf(User self) {
        this.self = self;
    }

    public User getSelf() {
        return self;
    }
}
