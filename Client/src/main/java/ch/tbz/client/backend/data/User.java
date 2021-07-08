package ch.tbz.client.backend.data;

import ch.tbz.client.backend.connection.Socket;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class User extends Person {
    private ArrayList<Group> groups;
    private ArrayList<Friend> friends;

    public User(long userId, String username, ArrayList<Group> groups, ArrayList<Friend> friends, boolean online){
        super(userId, username, online);
        this.groups = groups;
        this.friends = friends;
    }

    public void updateData(long userId, String username, ArrayList<Group> groups, ArrayList<Friend> friends, boolean online){
        this.userId = userId;
        this.username = username;
        this.groups = groups;
        this.friends = friends;
        this.online = online;
    }

    public void sendFriendRequest(int userId) {
        Socket.emit("sendFriendRequest", "{\"userid\":\"" + userId + "\"}");
    }

    public void createGroup(String groupName) {
        Socket.emit("createGroup", "{\"groupName\":\"" + groupName + "\"}");
    }

    public void logout() {
        Socket.emit("logout", "");
    }
}
