package org.epoxide.wumpus.discord.ws.request;

public class RequestHeartbeat {

    public String t;
    public Integer s;
    public Integer op;
    public Object d;

    public RequestHeartbeat() {}

    public RequestHeartbeat(Integer op, Object request) {
        this(null, null, op, request);
    }

    private RequestHeartbeat(String t, Integer s, Integer op, Object d) {
        this.t = t;
        this.s = s;
        this.op = op;
        this.d = d;
    }
}
