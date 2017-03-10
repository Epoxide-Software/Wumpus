package org.epoxide.wumpus.discord.ws.response.event;

import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.DiscordGateway;
import org.epoxide.wumpus.discord.ws.response.Data;

@DiscordGateway(opcode = 0)
public class Event implements Data {

    @Override
    public void onCall(Wumpus wumpus, EventWebSocket ws, Packet response) {
        System.out.println(this);
    }
}
