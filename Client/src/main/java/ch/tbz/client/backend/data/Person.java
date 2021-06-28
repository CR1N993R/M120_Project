package ch.tbz.client.backend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    protected long userId;
    protected String username;
    @Setter
    protected boolean online;
}
