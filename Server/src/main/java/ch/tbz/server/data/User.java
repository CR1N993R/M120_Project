package ch.tbz.server.data;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String uid;
    private ArrayList<User> friends = new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();
}
