package ch.tbz.client.backend.connection;

import ch.fenix.clientwrapper.Connection;
import ch.tbz.client.backend.data.DataParser;
import ch.tbz.client.backend.data.User;
import ch.tbz.client.backend.interfaces.*;
import lombok.Getter;
import org.json.simple.JSONArray;
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

    private static void attachListeners(){
        connection.setOn("getData", Socket::getData);
    }

    private static void login(String username, String password, MessageCallback callback){
        connection.setOn("login", (s) -> {
            connection.removeAllListenersByEvent("login");
            CallbackWrapper.sendMessage(callback, s);
        });
        connection.emit("login", "{\"username\":\""+ username +"\",\"password\",\"" + password + "\"}");
    }

    private static void register(String username, String password, MessageCallback callback){
        connection.setOn("register", (s) -> {
            connection.removeAllListenersByEvent("register");
            CallbackWrapper.sendMessage(callback, s);
        });
        connection.emit("createUser", "{\"username\":\""+ username +"\",\"password\",\"" + password + "\"}");
    }

    private static void getUsers(String name, UserCallback callback){
        connection.setOn("getUsers", (s) -> {
            connection.removeAllListenersByEvent("register");
            try {
                JSONArray array = (JSONArray) new JSONParser().parse(s);
                CallbackWrapper.sendPersons(callback, DataParser.parsePersons(array));
            } catch (ParseException ignored) { }
        });
        connection.emit("getUsersByName", "{\"name\":\""+ name +"\"}");
    }

    private static void getData(String msg){
        DataParser.parseData(msg, user);
        for (UpdateCallback listener : getDataListeners) {
            CallbackWrapper.update(listener);
        }
    }

    public static void addGetDataListener(UpdateCallback callback){
        getDataListeners.add(callback);
    }

    public static void clearGetDataListeners(){
        getDataListeners.clear();
    }
}
