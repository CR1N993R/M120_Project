package ch.tbz.server.data;

import ch.tbz.server.data.friend.FriendChat;
import ch.tbz.server.data.friend.UserToFriend;
import ch.tbz.server.data.group.GroupChat;
import ch.tbz.server.data.group.UserToGroup;
import ch.tbz.server.interfaces.MessageEvent;
import ch.tbz.server.util.Database;
import ch.tbz.server.util.security.Hashing;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long uid;
    @Column(name = "username", unique = true)
    @Setter
    private String username;
    @Column(name = "password")
    @Setter
    private String password;
    @Transient
    @Setter
    private MessageEvent messageEvent;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserToFriend> friends = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserToGroup> groups = new ArrayList<>();

    public User(String username, String password){
        try {
            this.username = username;
            this.password = Hashing.hash(password);
            Database.persistObject(this);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ignored) { }
    }

    public void sendMessageToFriend(String msg, int uid) {
        for (UserToFriend friend : friends) {
            User user0 = friend.getChat().getUsers().get(0).getUser();
            User user1 = friend.getChat().getUsers().get(1).getUser();
            if (user0.uid == uid || user1.uid == uid) {
                friend.getChat().addMessage(msg, this);
                return;
            }
        }
    }

    public void sendMessageToGroup(String msg, int gid) {
        for (UserToGroup group : groups) {
            if (group.getId() == gid) {
                group.getChat().addMessage(msg, this);
                return;
            }
        }
    }

    public void sendData() {
        if (messageEvent != null) {
            messageEvent.sendData();
        }
    }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("username", username);
        object.put("uid", uid);
        return object;
    }

    public JSONObject getJsonData() {
        JSONObject object = new JSONObject();
        object.put("friends", getFriendMessages());
        object.put("group", getGroupMessages());
        return object;
    }

    public JSONArray getFriendMessages() {
        JSONArray array = new JSONArray();
        for (UserToFriend friend : friends) {
            friend.toJson();
        }
        return array;
    }

    public JSONArray getGroupMessages() {
        JSONArray array = new JSONArray();
        for (UserToGroup group : groups) {
            group.toJson();
        }
        return array;
    }

    public void addFriend(User friend) {
        new FriendChat(this, friend);
    }

    public void declineFriend(int userid) {
        for (UserToFriend friend : friends) {
            if (friend.getFriend().uid == userid) {
                friend.getChat().decline();
                return;
            }
        }
    }

    public void acceptFriend(int userid) {
        for (UserToFriend friend : friends) {
            if (friend.getFriend().uid == userid) {
                friend.getChat().accept();
                return;
            }
        }
    }

    public void changeGroupName(int groupId, String name) {
        for (UserToGroup group : groups) {
            if (group.getId() == groupId) {
                group.getChat().setName(name);
                return;
            }
        }
    }

    public void leaveGroup(int groupId) {
        for (UserToGroup group : groups) {
            if (group.getId() == groupId) {
                group.leaveGroup();
                groups.remove(group);
                sendData();
                return;
            }
        }
    }

    public void addUserToGroup(int groupId, int userId) {
        GroupChat chat = Database.getGroupById(groupId);
        User user = Database.getUsersById(userId);
        for (UserToGroup group : groups) {
            if (group.getChat() == chat){
                chat.addUser(user);
                return;
            }
        }
    }

    public void createGroup(String groupName){
        UserToGroup userToGroup = new UserToGroup(this, groupName);
        this.groups.add(userToGroup);
        sendData();
    }

    public void groupMessagesRead(int groupId){
        for (UserToGroup group : groups) {
            if (group.getId() == groupId) {
                group.clearUnreadMessages();
                return;
            }
        }
    }

    public void friendMessagesRead(int friendId){
        for (UserToFriend friend : friends) {
            if (friend.getId() == friendId){
                friend.clearUnreadMessages();
                return;
            }
        }
    }
}
