package ch.tbz.client.backend.data;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Friend extends Person implements Chat {
    private final String state; //accepted, send, received
    private final ArrayList<Message> messages;
    private int newMessages;
    private boolean isOn;
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
