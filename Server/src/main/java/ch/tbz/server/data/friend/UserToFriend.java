package ch.tbz.server.data.friend;

import ch.tbz.server.data.User;
import ch.tbz.server.util.Database;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "user_to_friend")
@NoArgsConstructor
public class UserToFriend {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long id;
    @ManyToOne()
    @JoinColumn(name = "fk_user")
    private User user;
    @Column(name = "unread_messages")
    private int unreadMessages = 0;
    @Column(name = "state")
    private String state;
    @ManyToOne()
    @JoinColumn(name = "fk_chat")
    private FriendChat chat;

    public UserToFriend(User user, String state, FriendChat chat){
        this.chat = chat;
        this.user = user;
        this.state = state;
        user.getFriends().add(this);
        Database.persistObject(this);
    }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("id", getChat().getSecondUser(user).getUid());
        object.put("name", getChat().getSecondUser(user).getUsername());
        object.put("online", getChat().getSecondUser(user).isOnline());
        object.put("state", state);
        object.put("unreadMessages", unreadMessages);
        object.put("messages", getChat().getMessagesAsJson());
        return object;
    }

    public User getFriend() {
        return chat.getSecondUser(this.user);
    }

    public void decline() {
        user.getFriends().remove(this);
        user.sendData();
        Database.dropObject(this);
    }

    public void accept() {
        state = "accepted";
        user.sendData();
        Database.persistObject(this);
    }

    public void receivedMessage(){
        unreadMessages ++;
        user.sendData();
        Database.persistObject(this);
    }

    public void clearUnreadMessages(){
        unreadMessages = 0;
        Database.persistObject(this);
    }
}
