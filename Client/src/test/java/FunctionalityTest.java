import ch.fenix.clientwrapper.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class FunctionalityTest {
    Connection connection;

    @Before
    public void setUp() throws IOException {
        connection = new Connection("127.0.0.1", 25555);
    }

    @After
    public void disconnect() throws IOException {
        connection.close();
    }

    @Test
    public void testRegister() {

        connection.emit("createUser", "{\"username\":\"Cedric\",\"password\",\"Hello\"}");
    }
}
