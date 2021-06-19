package ch.tbz.server.connection;

import ch.fenix.serverwrapper.Connection;
import ch.tbz.server.data.User;
import ch.tbz.server.util.Database;
import ch.tbz.server.util.Json;
import ch.tbz.server.util.security.Hashing;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class Client {
    private final Connection connection;
    private User user;

    public Client(Connection connection) {
        this.connection = connection;
        connection.setOn("login", this::login);
        connection.setOn("createUser", this::createUser);
        connection.setOn("disconnect", this::disconnect);
    }

    public void login(String msg) {
        try {
            JSONObject object = (JSONObject) new JSONParser().parse(msg);
            User user = Database.getUserByUserNameOrId((String) object.get("username"));
            String password = Hashing.hash((String) object.get("password"));
            if (user.getPassword().equals(password)) {
                this.user = user;
                connection.emit("login", user.toJson().toJSONString());
                attachListeners();
            } else {
                connection.emit("login", "{\"success\": false}");
            }
        } catch (ParseException | InvalidKeySpecException | NoSuchAlgorithmException ignored) {
        }
    }

    public void attachListeners() {
        user.setMessageEvent(() -> getData(""));
        connection.setOn("logout", this::logout);
        connection.setOn("sendToUser", this::sendToUser);
        connection.setOn("sendToGroup", this::sendToGroup);
        connection.setOn("getData", this::getData);
        connection.setOn("sendFriendRequest", this::sendFriendRequest);
        connection.setOn("declineFriendRequest", this::declineFriendRequest);
        connection.setOn("acceptFriendRequest", this::acceptFriendRequest);
        connection.setOn("getUsersByName", this::getUsersByName);
        connection.setOn("changeGroupName", this::changeGroupName);
        connection.setOn("leaveGroup", this::leaveGroup);
        connection.setOn("addUserToGroup", this::addUserToGroup);
        connection.setOn("createGroup", this::createGroup);
        connection.setOn("groupMessagesRead", this::groupMessagesRead);
        connection.setOn("friendMessagesRead", this::friendMessagesRead);
    }

    public void logout(String msg) {
        user = null;
    }

    public void sendToUser(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            int userid = Math.toIntExact(Long.parseLong((String) jo.get("userid")));
            String message = (String) jo.get("msg");
            user.sendMessageToFriend(message, userid);
        }
    }

    public void sendToGroup(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            int userid = Math.toIntExact(Long.parseLong((String) jo.get("groupId")));
            String message = (String) jo.get("msg");
            user.sendMessageToGroup(message, userid);
        }
    }

    public void getData(String json) {
        connection.emit("getData", user.getJsonData().toJSONString());
    }

    public void sendFriendRequest(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            int userid = Math.toIntExact(Long.parseLong((String) jo.get("userid")));
            User user = Database.getUsersById(userid);
            user.addFriend(user);
        }
    }

    public void declineFriendRequest(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            int userid = Math.toIntExact(Long.parseLong((String) jo.get("userid")));
            user.declineFriend(userid);
        }
    }

    public void acceptFriendRequest(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            int userid = Math.toIntExact(Long.parseLong((String) jo.get("userid")));
            user.acceptFriend(userid);
        }
    }

    public void getUsersByName(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            String search = (String) jo.get("name");
            List<User> result = Database.getUsersByName(search);
            JSONArray jsonArray = new JSONArray();
            for (User user : result) {
                jsonArray.add(user.toJson());
            }
            connection.emit("getUsersByName", jsonArray.toJSONString());
        }
    }

    public void disconnect(String json) {
        System.out.println("disconnected");
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            this.user.setMessageEvent(null);
            this.user = null;
            connection.removeAllListeners();
        }
    }

    public void changeGroupName(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            int group = Math.toIntExact(Long.parseLong((String) jo.get("groupId")));
            String name = (String) jo.get("name");
            user.changeGroupName(group, name);
        }
    }

    public void leaveGroup(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            int group = Math.toIntExact(Long.parseLong((String) jo.get("groupId")));
            user.leaveGroup(group);
            user.sendData();
        }
    }

    public void addUserToGroup(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            int user = Math.toIntExact(Long.parseLong((String) jo.get("userid")));
            int group = Math.toIntExact(Long.parseLong((String) jo.get("groupId")));
            this.user.addUserToGroup(user, group);
        }
    }

    public void createGroup(String json){
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            String groupName = (String) jo.get("groupName");
            user.createGroup(groupName);
        }
    }

    public void groupMessagesRead(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            int groupId = Math.toIntExact(Long.parseLong((String) jo.get("groupId")));
            user.groupMessagesRead(groupId);
        }
    }

    public void friendMessagesRead(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            int userId = Math.toIntExact(Long.parseLong((String) jo.get("userid")));
            user.friendMessagesRead(userId);
        }
    }

    private void createUser(String json) {
        JSONObject jo = Json.parseJson(json);
        if (jo != null) {
            String username = (String) jo.get("username");
            String password = (String) jo.get("password");
            new User(username,password);
        }
    }
}
