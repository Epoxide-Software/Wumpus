package org.epoxide.wumpus.discord.ws.adapter;

import com.google.gson.*;
import org.epoxide.wumpus.discord.type.Channel;
import org.epoxide.wumpus.discord.type.Guild;
import org.epoxide.wumpus.discord.type.User;
import org.epoxide.wumpus.discord.type.registry.RegistryList;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChannelDeserializer implements JsonDeserializer<Channel> {

    @Override
    public Channel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String id = obj.get("id").getAsString();
        Channel guild = RegistryList.getChannel(id);
        if (guild == null) {
            guild = RegistryList.registerChannel(new Channel(id));

        }
        return guild;
    }
}
