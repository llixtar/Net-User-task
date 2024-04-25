package org.example.server;

import org.example.server.network.UserServer;

public class Main {
    public static void main(String[] args) throws Exception {
        UserServer server = new UserServer();
        server.start();
    }
}
