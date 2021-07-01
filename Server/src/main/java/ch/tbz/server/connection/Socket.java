package ch.tbz.server.connection;

import ch.fenix.serverwrapper.Connection;
import ch.fenix.serverwrapper.ServerSocketWrapper;
import lombok.Getter;

import java.io.IOException;

public abstract class Socket {
    @Getter
    private static ServerSocketWrapper serverSocketWrapper;

    public static void startServer(){
        try {
            serverSocketWrapper = new ServerSocketWrapper(25555);
            serverSocketWrapper.addOnConnection(Socket::clientConnected);
        } catch (IOException ignored) {}
    }

    public static void clientConnected(Connection connection){
        new Client(connection);
    }
}
