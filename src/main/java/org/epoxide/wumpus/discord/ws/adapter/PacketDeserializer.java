package org.epoxide.wumpus.discord.ws.adapter;

import com.google.gson.*;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.EventFactory;
import org.epoxide.wumpus.discord.ws.factory.GatewayFactory;
import org.epoxide.wumpus.discord.ws.response.Data;

import java.lang.reflect.Type;

public class PacketDeserializer implements JsonDeserializer<Packet> {

    @Override
    public Packet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject node = json.getAsJsonObject();

        Packet packet = new Packet();
        Class clazz;
        if (!node.get("t").isJsonNull()) {
            String event = node.get("t").getAsString();

            clazz = EventFactory.getEvent(event);
            if (clazz == null)
                throw new NullPointerException("Event not supported " + event);
        } else {
            int op = node.get("op").getAsInt();

            clazz = GatewayFactory.getGateway(op);
            packet.setOp(op);
            if (clazz == null)
                throw new NullPointerException("Gateway Op code not supported " + op);
        }
        if (!node.get("d").isJsonNull())
            packet.setData((Data) EventWebSocket.GSON.fromJson(node.get("d").toString(), clazz));
        return packet;
    }
}
