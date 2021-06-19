package ch.tbz.server.data.group;

import ch.tbz.server.data.Message;
import ch.tbz.server.data.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONArray;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "group_chat")
@NoArgsConstructor
public class GroupChat {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chat")
    private List<UserToGroup> users = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();
    @Setter
    @Column(name = "name")
    private String name;

    public GroupChat(String name, UserToGroup owner) {
        this.name = name;
        this.users.add(owner);
    }

    public void removeUser(UserToGroup user){
        users.remove(user);
        for (UserToGroup userToGroup : users) {
            userToGroup.getUser().sendData();
        }
    }

    public void addUser(User user){
        this.users.add(new UserToGroup(user, this));
        for (UserToGroup userToGroup : users) {
            userToGroup.getUser().sendData();
        }
    }

    public void addMessage(String text, User user){
        Message message = new Message(text,user);
        messages.add(message);
        for (UserToGroup userToChat : users) {
            userToChat.receivedMessage();
        }
    }

    public JSONArray getUsersAsJson(){
        JSONArray array = new JSONArray();
        for (UserToGroup user : users) {
            array.add(user.toJson());
        }
        return array;
    }

    public JSONArray getMessagesAsJson(){
        JSONArray array = new JSONArray();
        for (Message message : messages) {
            array.add(message.toJson());
        }
        return array;
    }
}
