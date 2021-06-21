package ch.tbz.client.backend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Person {
    protected final long userId;
    protected final String username;

    public long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }
}
