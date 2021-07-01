package ch.tbz.client.backend.data;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.interfaces.Chat;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Friend extends Person implements Chat {
    private final String state; //accepted, send, received
    private final ArrayList<Message> messages;
    private final int unreadMessages;

    public Friend(int userId, String name, String state, ArrayList<Message> messages, int unreadMessages, boolean online){
        super(userId, name, online);
        this.state = state;
        this.messages = messages;
        this.unreadMessages = unreadMessages;
    }

    public void acceptFriendRequest(){
        if (state.equals("received")){
            Socket.emit("acceptFriendRequest", "{\"userid\":\"" + this.userId + "\"}");
        }
    }

    public void friendMessagesRead() {
        Socket.emit("friendMessagesRead", "{\"userid\":\"" + this.userId + "\"}");
    }

    public void declineFriendRequest() {
        Socket.emit("declineFriendRequest", "{\"userid\":\"" + this.userId + "\"}");
    }

    public String getState() {
        return state;
    }

    public void sendToFriend(String msg){
        Socket.emit("sendToUser", "{\"userid\":\"" + userId + ", \"msg\":\"" + msg + "\"}");
    }
}
