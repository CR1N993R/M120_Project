package ch.tbz.client.backend.data;

import ch.tbz.client.backend.connection.Socket;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Friend extends Person implements Chat {
    private final String state; //accepted, send, received
    private final ArrayList<Message> messages;
    private boolean isOn;
    private final int unreadMessages;

    public Friend(int userId, String name, String state, ArrayList<Message> messages, int unreadMessages, boolean online){
        super(userId, name, online);
        this.state = state;
        this.messages = messages;
        this.unreadMessages = unreadMessages;
    }

    public void acceptFriendRequest(long userId){
        if (state.equals("received")){
            Socket.emit("acceptFriendRequest", "{\"userid\":\"" + userId + "\"}");
        }
    }

    public void friendMessagesRead(long userId) {
        Socket.emit("friendMessagesRead", "{\"userid\":\"" + userId + "\"}");
    }

    public void declineFriendRequest(long userId) {
        Socket.emit("declineFriendRequest", "{\"userid\":\"" + userId + "\"}");
    }

    public String getState() {
        return state;
    }

    public boolean isOn() {
        return isOn;
    }

    public void sendToFriend(long userId, String msg){
        Socket.emit("sendToUser", "{\"userid\":\"" + userId + ", \"msg\":\"" + msg + "\"}");
    }
}
