package ch.tbz.server.data;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long id;
    @Column(name = "name")
    @Setter
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    private ArrayList<Message> messages = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private ArrayList<User> users = new ArrayList<>();
}
