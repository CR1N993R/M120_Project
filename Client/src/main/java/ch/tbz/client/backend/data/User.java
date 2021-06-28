package ch.tbz.client.backend.data;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class User extends Person {
    private ArrayList<Group> groups;
    private ArrayList<Friend> friends;

    public User(long userId, String username, ArrayList<Group> groups, ArrayList<Friend> friends, boolean online){
        super(userId, username, online);
        this.groups = groups;
        this.friends = friends;
    }

    public void updateData(long userId, String username, ArrayList<Group> groups, ArrayList<Friend> friends, boolean online){
        this.userId = userId;
        this.username = username;
        this.groups = groups;
        this.friends = friends;
    }
}
