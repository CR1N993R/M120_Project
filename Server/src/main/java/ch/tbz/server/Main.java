package ch.tbz.server;

import ch.tbz.server.connection.Socket;
import ch.tbz.server.util.Database;

public class Main {
    public static void main(String[] args) {
        Database.init();
        Socket.startServer();
    }
}
