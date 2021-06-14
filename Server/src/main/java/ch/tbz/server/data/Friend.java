package ch.tbz.server.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Entity
@Table(name = "friends")
@NoArgsConstructor
public class Friend {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user1;
    @Column(name = "user1_messages")
    private int user1UnreadMessages;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user2;
    @Column(name = "user2_messages")
    private int user2UnreadMessages;
    @OneToMany(fetch = FetchType.LAZY)
    private ArrayList<Message> messages = new ArrayList<>();
    @Column(name = "status")
    @Setter
    private String status;

    public Friend(User user1, User user2){
        this.user1 = user1;
        this.user2 = user2;
        user1UnreadMessages = 0;
        user2UnreadMessages = 0;
        status = "request_sent";
    }

    public void createMessage(String msg, User user){
        messages.add(new Message(msg,user));
        if (!user1.isLoggedIn()){
            user1UnreadMessages ++;
        }
    }
}
