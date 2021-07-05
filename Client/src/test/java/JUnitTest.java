import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.backend.data.Group;
import ch.tbz.client.backend.data.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class JUnitTest {

    @Test
    public void userTest() {
        User user = new User(1, "test", new ArrayList<Group>(), new ArrayList<Friend>(), true);
        Assert.assertEquals(1, user.getUserId());
        Assert.assertEquals("test", user.getUsername());
        Assert.assertTrue(user.getGroups().isEmpty());
        Assert.assertTrue(null, user.getFriends().isEmpty());
        Assert.assertTrue(user.isOnline());
    }

    @Test
    public void groupTest() {
        Socket.getUser().createGroup("testGroup");
        Assert.assertEquals("testGroup", Socket.getUser().getGroups().get(0).getGroupName());
    }
    
}
