package ch.tbz.client.backend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class Group implements Chat{
    private final String groupname;
    private final long groupId;
    private final String name;
    private final int unreadMessages;
    private final ArrayList<Person> users;
    private final ArrayList<Message> messages;

    @Override
    public ArrayList<Message> getMessage() {
        return this.messages;
    }
}
