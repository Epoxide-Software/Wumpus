package org.epoxide.wumpus;

import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.factory.FactoryBus;
import org.epoxide.wumpus.utils.Endpoints;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class Wumpus {

    public static final Endpoints ENDPOINTS = new Retrofit.Builder()
            .baseUrl("https://discordapp.com/api/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(Endpoints.class);

    private final String token;
    private final EventWebSocket websocket;

    public Wumpus(String token) {
        this.token = token;
        this.websocket = new EventWebSocket(this);

        FactoryBus.autoSearch();
    }

    public String getToken() {
        return token;
    }
}
