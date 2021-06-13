package ch.tbz.server.connection;

import ch.fenix.serverwrapper.Connection;
import ch.tbz.server.data.User;
import ch.tbz.server.util.Database;
import ch.tbz.server.util.security.Hashing;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Client {
    private final Connection connection;
    private User user;

    public Client(Connection connection) {
        this.connection = connection;
        connection.setOn("login", this::login);
    }

    public void login(String msg) {
        try {
            JSONObject object = (JSONObject) new JSONParser().parse(msg);
            User user = Database.getUserByUserNameOrId((String) object.get("username"));
            String password = Hashing.hash((String) object.get("password"));
            if (user.getPassword().equals(password)) {
                this.user = user;
                connection.emit("login", user.toJson());
                attachListeners();
            } else {
                connection.emit("login", "{\"success\": false}");
            }
        } catch (ParseException | InvalidKeySpecException | NoSuchAlgorithmException ignored) {
        }
    }

    public void attachListeners() {
        connection.setOn("logout", this::logout);
        connection.setOn("sendToUser", this::sendToUser);
        connection.setOn("sendToGroup", this::sendToGroup);
        connection.setOn("getData", this::getData);
        connection.setOn("sendFriendRequest", this::sendFriendRequest);
        connection.setOn("declineFriendRequest", this::declineFriendRequest);
        connection.setOn("acceptFriendRequest", this::acceptFriendRequest);
        connection.setOn("getUsersByName", this::getUsersByName);
        connection.setOn("disconnect", this::disconnect);
    }

    public void logout(String msg) {
        user = null;
    }

    public void sendToUser(String msg) {

    }

    public void sendToGroup(String msg) {

    }

    public void getData(String msg) {

    }

    public void sendFriendRequest(String msg) {

    }

    public void declineFriendRequest(String msg) {

    }

    public void acceptFriendRequest(String msg) {

    }

    public void getUsersByName(String msg) {

    }

    public void disconnect(String msg) {

    }
}