package org.epoxide.wumpus.discord.ws.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.epoxide.wumpus.discord.type.User;
import org.epoxide.wumpus.discord.type.registry.RegistryList;

import java.lang.reflect.Type;

public class UserDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

        JsonObject obj = json.getAsJsonObject();
        String id = obj.get("id").getAsString();
        User user = RegistryList.getUser(id);
        if (user == null) {
            user = RegistryList.registerUser(new User(id));
            user.setUsername(obj.get("username").getAsString());
            user.setDiscriminator(obj.get("discriminator").getAsString());
            user.setBot(obj.has("bot") && obj.get("bot").getAsBoolean());
            user.setAvatar((obj.has("avatar") && !obj.get("avatar").isJsonNull()) ? obj.get("avatar").getAsString() : null);
        }
        return user;
    }
}
