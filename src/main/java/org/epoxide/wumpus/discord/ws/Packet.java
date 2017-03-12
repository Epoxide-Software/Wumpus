package org.epoxide.wumpus.discord.ws;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.epoxide.wumpus.discord.ws.response.Data;

public class Packet {

    @Expose
    private int op;
    @Expose
    private String t;
    @Expose
    private String s;
    @Expose
    @SerializedName("d")
    private Object object;

    public Packet() {
    }

    public Packet(int op, Object object) {
        this.op = op;
        this.object = object;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
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
