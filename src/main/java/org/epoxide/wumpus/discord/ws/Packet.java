package org.epoxide.wumpus.discord.ws;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.epoxide.wumpus.discord.ws.adapter.PacketDeserializer;
import org.epoxide.wumpus.discord.ws.response.Data;

@JsonDeserialize(using = PacketDeserializer.class)
public class Packet {

    private int op;

    @JsonProperty("d")
    private Data data;

    private String t;
    private String s;

    public Packet() {
    }

    public Packet(int op, Data data) {
        this.op = op;
        this.data = data;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
