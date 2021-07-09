package ch.tbz.client.backend.util;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DataParser {
    public static void parseData(String json, User user) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(json);
            long userId = (Long) object.get("uid");
            String userName = (String) object.get("username");
            boolean loggedIn = (boolean) object.get("online");
            ArrayList<Friend> friends = parseFriends((JSONArray) object.get("friends"));
            ArrayList<Group> groups = parseGroups((JSONArray) object.get("group"));
            user.updateData(userId, userName, groups, friends, loggedIn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Friend> parseFriends(JSONArray friends) {
        ArrayList<Friend> friendList = new ArrayList<>();
        if(friends != null) {
            for (Object friendObj : friends) {
                friendList.add(parseFriend((JSONObject) friendObj));
            }
        }
        return friendList;
    }

    private static ArrayList<Group> parseGroups(JSONArray groups) {
        ArrayList<Group> groupList = new ArrayList<>();
        if(groups != null) {
            for (Object group : groups) {
                groupList.add(parseGroup((JSONObject) group));
            }
        }
        return groupList;
    }

    private static Group parseGroup(JSONObject o) {
        int id = Math.toIntExact((Long) o.get("id"));
        int unreadMessages = Math.toIntExact((Long) o.get("unreadMessages"));
        String name = (String) o.get("name");
        return new Group(id, name, unreadMessages, parsePersons((JSONArray) o.get("users")), parseMessages((JSONArray) o.get("messages")));
    }

    private static ArrayList<Message> parseMessages(JSONArray array) {
        ArrayList<Message> messages = new ArrayList<>();
        if (array != null) {
            for (Object o : array) {
                messages.add(parseMessage((JSONObject) o));
            }
        }
        return messages;
    }

    private static Message parseMessage(JSONObject o) {
        int id = Math.toIntExact((Long) o.get("id"));
        String text = (String) o.get("text");
        Person person = parsePerson((JSONObject) o.get("user"));
        LocalDateTime ldt = LocalDateTime.parse((String) o.get("time"), DateTimeFormatter.ISO_DATE_TIME);
        return new Message(id, text, person, ldt);
    }

    public static ArrayList<Person> parsePersons(JSONArray array) {
        ArrayList<Person> members = new ArrayList<>();
        if(array != null) {
            for (Object o : array) {
                members.add(parsePerson((JSONObject) o));
            }
        }
        return members;
    }

    private static Friend parseFriend(JSONObject o) {
        int id = Math.toIntExact((Long) o.get("id"));
        String name = (String) o.get("name");
        boolean online = (boolean) o.get("online");
        int unreadMessages = Math.toIntExact((Long) o.get("unreadMessages"));
        String state = (String) o.get("state");
        ArrayList<Message> messages = parseMessages((JSONArray) o.get("messages"));
        return new Friend(id, name, state, messages, unreadMessages, online);
    }

    public static Person parsePerson(JSONObject o) {
        int userId = Math.toIntExact((Long) o.get("uid"));
        String userName = (String) o.get("username");
        boolean online = (boolean) o.get("online");
        return new Person(userId, userName, online);
    }

    public static void updateUserState(String json){
        try {
            JSONObject obj = (JSONObject) new JSONParser().parse(json);
            boolean isGroup = (boolean) obj.get("isGroup");
            long userId = Long.parseLong((String) obj.get("userId"));
            boolean state = (boolean) obj.get("state");
            if (isGroup){
                updateGroupMemberState(userId, state);
            }else{
                updateFriendState(userId, state);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void updateFriendState(long userId, boolean online){
        for (Friend friend : Socket.getUser().getFriends()) {
            if (friend.getUserId() == userId) {
                friend.setOnline(online);
            }
        }
    }

    private static void updateGroupMemberState(long userId, boolean online) {
        for (Group group : Socket.getUser().getGroups()) {
            for (Person user : group.getUsers()) {
                if (user.getUserId() == userId){
                    user.setOnline(online);
                }
            }
        }
    }
}
