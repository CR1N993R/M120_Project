import ch.fenix.clientwrapper.Connection;

import java.io.IOException;

public class FunctionalityTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Connection connection = new Connection("127.0.0.1", 25555);
        Thread.sleep(100);
        connection.setOn("login", (s) -> {
            System.out.println(s);
            try {
                Thread.sleep(100);
                connection.close();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });
        connection.emit("login", "{\"username\":\"Cedric\",\"password\":\"Hello\"}");
    }
}
