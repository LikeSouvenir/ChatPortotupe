package org.example;

import java.util.Timer;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // server

        Server server = new Server();
        server.connectClient(new Client());
        server.connectClient(new Client());
    }
}
