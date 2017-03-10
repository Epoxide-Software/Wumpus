package org.epoxide.wumpus.discord.ws.response;

import org.epoxide.wumpus.Wumpus;
import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;

public interface Data {

    void onCall(Wumpus wumpus, EventWebSocket ws, Packet response);
}
