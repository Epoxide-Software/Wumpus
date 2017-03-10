package org.epoxide.wumpus;

import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.epoxide.annotationfactory.FactoryBus;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.User;
import org.epoxide.wumpus.utils.Endpoints;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.URI;

public class Wumpus {

    public static final Endpoints ENDPOINTS = new Retrofit.Builder()
            .baseUrl("https://discordapp.com/api/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(Endpoints.class);

    private static Wumpus INSTANCE;
    private final String token;
    private final WebSocketClient ws;
    private final EventWebSocket websocket;
    private User self;

    public Wumpus(String token, int[] shards, int shardCount) {
        INSTANCE = this;

        this.token = token;
        for (int shardID : shards) {
            if (shardID >= shardCount) {
                //TODO Error
            }
        }
        //TODO move to shard
        this.websocket = new EventWebSocket();
        this.ws = new WebSocketClient(new SslContextFactory());
        try {
            this.ws.start();
            this.ws.connect(this.websocket, new URI(Wumpus.ENDPOINTS.getGateway().execute().body().url + "?v=5&encoding=json"), new ClientUpgradeRequest());
        } catch (Exception e) {
            e.printStackTrace();
        }

        FactoryBus.autoSearch();
    }

    public String getToken() {
        return token;
    }

    public void setSelf(User self) {
        this.self = self;
    }

    public static Wumpus getInstance() {
        return INSTANCE;
    }
}
