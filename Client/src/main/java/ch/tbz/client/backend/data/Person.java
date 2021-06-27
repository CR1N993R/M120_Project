package ch.tbz.client.backend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Person {
    protected long userId;
    protected String username;
    protected boolean online;
}
