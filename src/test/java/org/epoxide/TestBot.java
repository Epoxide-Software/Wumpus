package org.epoxide;

import org.epoxide.wumpus.ClientBuilder;

public class TestBot {

    public static void main(String[] args) {
        if (args.length == 1) {
            ClientBuilder clientBuilder = new ClientBuilder();
            clientBuilder.setToken(args[0]);
            clientBuilder.login();
        } else {
            System.err.println("Need token");
        }
    }
}
