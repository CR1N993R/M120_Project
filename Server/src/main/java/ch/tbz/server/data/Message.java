package ch.tbz.server.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Entity
@Table(name = "messages")
@NoArgsConstructor
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementor")
    @GenericGenerator(name = "incrementor", strategy = "increment")
    private long id;
    @Column(name = "content")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_sent_by")
    private User sentBy;
    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    public Message(String msg, User sender){
        content = msg;
        sentBy = sender;
        sentAt = LocalDateTime.now();
    }

    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("id", id);
        object.put("text",content);
        object.put("user", sentBy.toJson());
        object.put("time", timeToString());
        return object;
    }

    public String timeToString(){
        return sentAt.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
