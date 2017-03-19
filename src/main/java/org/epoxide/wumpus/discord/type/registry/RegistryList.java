package org.epoxide.wumpus.discord.type.registry;

import org.epoxide.wumpus.discord.type.Channel;
import org.epoxide.wumpus.discord.type.Guild;
import org.epoxide.wumpus.discord.type.User;

import java.util.HashMap;
import java.util.Map;

public class RegistryList {

    private static final Map<String, Guild> GUILD_MAP = new HashMap<>();
    private static final Map<String, User> USER_MAP = new HashMap<>();
    private static final Map<String, Channel> CHANNEL_MAP = new HashMap<>();

    public static Guild registerGuild(Guild guild) {

        GUILD_MAP.put(guild.getId(), guild);
        return guild;
    }

    public static Guild getGuild(String id) {

        return GUILD_MAP.get(id);
    }

    public static User registerUser(User user) {

        USER_MAP.put(user.getId(), user);
        return user;
    }

    public static User getUser(String id) {

        return USER_MAP.get(id);
    }

    public static Channel registerChannel(Channel channel) {

        CHANNEL_MAP.put(channel.getId(), channel);
        return channel;
    }


    public static Channel getChannel(String id) {

        return CHANNEL_MAP.get(id);
    }
}
