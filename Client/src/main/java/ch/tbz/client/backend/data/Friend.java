package ch.tbz.client.backend.data;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Friend extends Person {
    private final String state;
    private final ArrayList<Message> messages;
    private final int unreadMessages;

    public Friend(int userId, String name, String state, ArrayList<Message> messages, int unreadMessages, boolean online){
        super(userId, name, online);
        this.state = state;
        this.messages = messages;
        this.unreadMessages = unreadMessages;
    }

    public void accept(){
        if (state.equals("received")){

        }
    }
}
