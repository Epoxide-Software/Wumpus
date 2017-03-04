package org.epoxide.wumpus.utils;

import org.epoxide.wumpus.discord.response.ResponseGateway;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Endpoints {

    @GET("gateway")
    Call<ResponseGateway> getGateway();

    @GET("gateway/bot")
    Call<ResponseGateway> getGatewayBot();
}
