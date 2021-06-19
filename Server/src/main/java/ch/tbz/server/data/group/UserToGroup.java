package ch.tbz.server.data.group;

import ch.tbz.server.data.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "user_to_group")
@NoArgsConstructor
public class UserToGroup {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User user;
    @Column(name = "unread_messages")
    private int unreadMessages = 0;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_chat")
    private GroupChat chat;

    public UserToGroup(User user, GroupChat groupChat){
        groupChat.getUsers().add(this);
        this.chat = groupChat;
        this.user = user;
    }

    public UserToGroup(User user, String groupName){
        this.user = user;
        chat = new GroupChat(groupName, this);
    }

    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("unreadMessages", unreadMessages);
        object.put("users", getChat().getUsersAsJson());
        object.put("message", getChat().getMessagesAsJson());
        return object;
    }

    public void leaveGroup() {
        chat.removeUser(this);
    }

    public void receivedMessage(){
        unreadMessages ++;
        user.sendData();
    }

    public void clearUnreadMessages(){
        unreadMessages = 0;
    }
}
