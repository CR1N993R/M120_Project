package ch.tbz.server.data.friend;

import ch.tbz.server.data.Message;
import ch.tbz.server.data.User;
import ch.tbz.server.util.Database;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "friend_chats")
@NoArgsConstructor
public class FriendChat {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long id;
    @OneToMany(mappedBy = "chat")
    private List<UserToFriend> users = new ArrayList<>();
    @OneToMany()
    private List<Message> messages = new ArrayList<>();

    public FriendChat(User user, User friend){
        Database.persistObject(this);
        users.add(new UserToFriend(user, "sent", this));
        users.add(new UserToFriend(friend, "received", this));
        sendUpdateUsers();
    }

    public void sendUpdateUsers() {
        for (UserToFriend user : users) {
            user.getUser().sendData();
        }
    }

    public void addMessage(String text, User user){
        Message message = new Message(text, user);
        messages.add(message);
        users.get(0).receivedMessage();
        users.get(1).receivedMessage();
        Database.persistObject(this);
    }

    public JSONArray getMessagesAsJson(){
        JSONArray array = new JSONArray();
        for (Message message : messages) {
            array.add(message.toJson());
        }
        return array;
    }

    public User getSecondUser(User current){
        if (users.get(0).getUser() == current) {
            return users.get(1).getUser();
        }else {
            return users.get(0).getUser();
        }
    }

    public void decline(){
        users.get(0).decline();
        users.get(1).decline();
        Database.dropObject(this);
    }

    public void accept(){
        users.get(0).accept();
        users.get(1).accept();
        Database.persistObject(this);
    }

    public void sendUpdateState(User user){
        User friend = getSecondUser(user);
        friend.emit("updateUserStatus", "{\"isGroup\":false, \"userId\":\"" + user.getUid() + "\",\"state\":" + user.isOnline() + "}");
    }
}
