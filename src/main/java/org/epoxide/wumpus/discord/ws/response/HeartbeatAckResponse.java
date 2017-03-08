package org.epoxide.wumpus.discord.ws.response;

import org.epoxide.wumpus.discord.EventWebSocket;
import org.epoxide.wumpus.discord.ws.Packet;
import org.epoxide.wumpus.discord.ws.factory.DiscordGateway;

@DiscordGateway(opcode = 11)
public class HeartbeatAckResponse implements Data {

    private String d;

    @Override
    public void onCall(Packet response, EventWebSocket webSocket) {
        //TODO Tell the heartbeat it saw the request
        System.out.println("ACK");
    }
}
