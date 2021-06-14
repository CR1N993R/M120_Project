package ch.tbz.server.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private User sentBy;
    @Column(name = "sentAt")
    private LocalDateTime sentAt;

    public Message(String content, User sentBy) {
        this.content = content;
        this.sentBy = sentBy;
        sentAt = LocalDateTime.now();
    }
}
