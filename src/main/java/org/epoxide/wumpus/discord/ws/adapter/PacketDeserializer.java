package org.epoxide.wumpus.discord.ws.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.EventFactory;
import org.epoxide.wumpus.discord.ws.factory.GatewayFactory;
import org.epoxide.wumpus.discord.ws.response.Data;

import java.io.IOException;

public class PacketDeserializer extends JsonDeserializer<Packet> {

    @Override
    public Packet deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        int op = node.get("op").asInt();
        String event = node.get("t").asText();

        Packet packet = new Packet();
        Class clazz;
        if (!node.get("t").isNull()) {
            clazz = EventFactory.getEvent(event);
            if (clazz == null)
                throw new NullPointerException("Event not supported " + event);
        } else {
            clazz = GatewayFactory.getGateway(op);
            if (clazz == null)
                throw new NullPointerException("Gateway Op code not supported " + op);
        }

        if (!node.get("d").isNull())
            packet.setData((Data) EventWebSocket.JACKSON.readValue(node.get("d").toString(), clazz));
        return packet;

    }
}
