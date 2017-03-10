package org.epoxide.wumpus.discord.ws;

import com.google.gson.annotations.SerializedName;
import org.epoxide.wumpus.discord.ws.response.Data;

public class Packet {

    private int op;

    @SerializedName("d")
    private Object data;

    private String t;
    private String s;

    public Packet() {
    }

    public Packet(int op, Object data) {
        this.op = op;
        this.data = data;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public Object getData() {
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
