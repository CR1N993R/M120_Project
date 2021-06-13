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
    @ManyToOne(fetch = FetchType.LAZY)
    private User user2;
    @OneToMany(fetch = FetchType.LAZY)
    private ArrayList<Message> messages = new ArrayList<>();
    @Column(name = "status")
    @Setter
    private String status;

    public void createMessage(String msg, User user){
        messages.add(new Message(msg,user));
    }
}
