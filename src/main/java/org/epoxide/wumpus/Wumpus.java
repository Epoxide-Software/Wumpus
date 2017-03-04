package org.epoxide.wumpus;

import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.utils.Endpoints;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Wumpus {

    public static final Endpoints ENDPOINTS = new Retrofit.Builder()
            .baseUrl("https://discordapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Endpoints.class);

    private final String token;
    private final Object websocket;

    public Wumpus(String token) {
        this.token = token;

        this.websocket = new EventWebSocket(this);
        System.out.println(ENDPOINTS.getGateway().shards);
    }
}
