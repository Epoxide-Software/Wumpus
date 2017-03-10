package org.epoxide.wumpus;

public class ClientBuilder {

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public Wumpus login() {

        return new Wumpus(token, new int[0], 1);
    }
}
