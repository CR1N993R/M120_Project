package ch.tbz.server.data;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private long uid;
    @Column(name = "username")
    @Setter
    private String username;
    @Column(name = "password")
    @Setter
    private String password;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @Transient
    private boolean loggedIn;
    @OneToMany(fetch = FetchType.LAZY)
    private ArrayList<Friend> friends = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private ArrayList<Group> groups = new ArrayList<>();

    public String toJson(){
        JSONObject object = new JSONObject();
        object.put("username", username);
        object.put("success", true);
        object.put("uid", uid);
        return object.toJSONString();
    }

    public void sendMessage(String msg, int uid){
        for (Friend friend : friends) {
            if (friend.getUser1().uid == uid || friend.getUser2().uid == uid){
                friend.createMessage(msg, this);
            }
        }
    }
}
