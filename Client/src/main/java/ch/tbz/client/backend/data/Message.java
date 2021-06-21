package ch.tbz.client.backend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Message {
    private final long id;
    private final String message;
    private final Person sender;
    private final LocalDateTime sentAt;

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Person getSender() {
        return sender;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}
