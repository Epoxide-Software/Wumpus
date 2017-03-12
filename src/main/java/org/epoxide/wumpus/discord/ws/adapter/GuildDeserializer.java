package org.epoxide.wumpus.discord.ws.adapter;

import com.google.gson.*;
import org.epoxide.wumpus.discord.Guild;
import org.epoxide.wumpus.discord.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GuildDeserializer implements JsonDeserializer<Guild> {

    @Override
    public Guild deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

        JsonObject obj = json.getAsJsonObject();
        String id = obj.get("id").getAsString();
        Guild guild = null/*GuildList.get(id)*/;
        if (guild == null) {
            guild = new Guild(id);
            if (obj.has("name"))
                guild.setName(obj.get("name").getAsString());
            if (obj.has("unavailable"))
                guild.setUnavailable(obj.get("unavailable").getAsBoolean());
            if (obj.has("verification_level"))
                guild.setVerificationLevel(obj.get("verification_level").getAsInt());
            if (obj.has("region"))
                guild.setRegion(obj.get("region").getAsString());
//            if (obj.has("owner_id"))
//                guild.setOwner(UserList.get(obj.get("owner_id").getAsString());
            if (obj.has("mfa_level"))
                guild.setMfaLevel(obj.get("mfa_level").getAsInt());
            if (obj.has("member_count"))
                guild.setMemberCount(obj.get("member_count").getAsInt());
            if (obj.has("large"))
                guild.setLarge(obj.get("large").getAsBoolean());
            if (obj.has("icon"))
                guild.setIcon(obj.get("icon").getAsString());
            if (obj.has("default_message_notifications"))
                guild.setDefaultMessageNotifications(obj.get("default_message_notifications").getAsInt());
            if (obj.has("afk_timeout"))
                guild.setAfkTimeout(obj.get("afk_timeout").getAsInt());
            //TODO Change to channel
            if (obj.has("afk_channel_id"))
                guild.setAfkChannelID(obj.get("afk_channel_id").getAsString());

            if (obj.has("members")) {
                JsonArray rawMembers = obj.get("members").getAsJsonArray();
                List<User> members = new ArrayList<>();
                for (JsonElement e : rawMembers) {
                    JsonElement userElement = e.getAsJsonObject().get("user");
                    String userID = userElement.getAsJsonObject().get("id").getAsString();
                    User user = null/*UserList.get(userID)*/;
                    if (user == null) {
                        user = context.deserialize(userElement, User.class);
                    }
                    members.add(user);
                }
                guild.setMembers(members);
            }
        }
        return guild;
    }
}
