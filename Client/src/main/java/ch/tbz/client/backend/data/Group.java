package ch.tbz.client.backend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class Group {
    private final long groupId;
    private final ArrayList<Person> users;
    private final ArrayList<Message> messages;
}
