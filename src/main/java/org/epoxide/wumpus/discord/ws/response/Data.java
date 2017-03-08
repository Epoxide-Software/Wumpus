package org.epoxide.wumpus.discord.ws.response;

import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;

public interface Data {

    void onCall(Packet response, EventWebSocket webSocket);
}
