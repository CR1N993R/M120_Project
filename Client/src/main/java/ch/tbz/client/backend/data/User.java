package ch.tbz.client.backend.data;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class User extends Person {
    private final ArrayList<Group> groups;
    private final ArrayList<Friend> friends;

    public User(int userId, String username, ArrayList<Group> groups, ArrayList<Friend> friends){
        super(userId, username);
        this.groups = groups;
        this.friends = friends;
    }
}
