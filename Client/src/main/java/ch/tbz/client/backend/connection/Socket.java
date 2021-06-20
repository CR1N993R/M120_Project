package ch.tbz.client.backend.connection;

import ch.fenix.clientwrapper.Connection;

import java.io.IOException;

public class Socket {
    private static Connection connection;

    public static void init() {
        try {
            connection = new Connection("127.0.0.1", 25555);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void attachListeners(){

    }
}
