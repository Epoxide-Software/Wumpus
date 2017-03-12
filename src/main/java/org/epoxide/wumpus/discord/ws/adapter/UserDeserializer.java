package org.epoxide.wumpus.discord.ws.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.epoxide.wumpus.discord.User;

import java.lang.reflect.Type;

public class UserDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

        JsonObject obj = json.getAsJsonObject();
        String id = obj.get("id").getAsString();
        User user = null/*UserList.get(id)*/;
        if (user == null) {
            String username = obj.get("username").getAsString();
            String discriminator = obj.get("discriminator").getAsString();
            boolean bot = obj.has("bot") && obj.get("bot").getAsBoolean();
            String avatar = (obj.has("avatar") && !obj.get("avatar").isJsonNull()) ? obj.get("avatar").getAsString() : null;
            user = new User(id);
            user.setUsername(username);
            user.setDiscriminator(discriminator);
            user.setBot(bot);
            user.setAvatar(avatar);
        }
        return user;
    }
}
