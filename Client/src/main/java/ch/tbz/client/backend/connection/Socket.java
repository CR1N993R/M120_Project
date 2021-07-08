package ch.tbz.client.backend.connection;

import ch.fenix.clientwrapper.Connection;
import ch.tbz.client.backend.util.DataParser;
import ch.tbz.client.backend.data.User;
import ch.tbz.client.backend.interfaces.*;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class Socket {
    private static Connection connection;
    private static ArrayList<UpdateCallback> getDataListeners;
    @Getter
    private static User user;

    public static void init() {
        try {
            getDataListeners = new ArrayList<>();
            connection = new Connection("127.0.0.1", 25555);
            attachListeners();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void attachListeners() {
        connection.setOn("getData", Socket::getData);
        connection.setOn("updateUserStatus", Socket::updateUserStatus);
    }

    private static void updateUserStatus(String s) {
        DataParser.updateUserState(s);
        for (UpdateCallback listener : getDataListeners) {
            CallbackWrapper.update(listener);
        }
    }

    private static void getData(String msg) {
        if (user == null) {
            user = new User();
        }
        DataParser.parseData(msg, user);
        for (UpdateCallback listener : getDataListeners) {
            CallbackWrapper.update(listener);
        }
    }

    public static void emit(String event, String message) {
        connection.emit(event, message);
    }

    public static void login(String username, String password, MessageCallback callback) {
        connection.setOn("login", (s) -> {
            connection.removeAllListenersByEvent("login");
            CallbackWrapper.sendMessage(callback, s);
        });
        connection.emit("login", "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}");
    }

    public static void getUser(String id, UserCallback callback) {
        connection.setOn("getUserById", (s) -> {
            connection.removeAllListenersByEvent("getUserById");
            try {
                if (s.length() > 0) {
                    JSONObject user = (JSONObject) new JSONParser().parse(s);
                    CallbackWrapper.sendPersons(callback, DataParser.parsePerson(user));
                } else {
                    CallbackWrapper.sendPersons(callback, null);
                }
            } catch (ParseException ignored) {
            }
        });
        connection.emit("getUserById", "{\"id\":\"" + id + "\"}");
    }

    public static void register(String username, String password, MessageCallback callback) {
        connection.setOn("register", (s) -> {
            connection.removeAllListenersByEvent("register");
            CallbackWrapper.sendMessage(callback, s);
        });
        connection.emit("createUser", "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}");
    }

    public static void addGetDataListener(UpdateCallback callback) {
        getDataListeners.add(callback);
    }

    public static void clearGetDataListeners() {
        getDataListeners.clear();
    }

    public static void close() {
        try {
            connection.close();
        } catch (IOException ignored) {
        }
    }
}
