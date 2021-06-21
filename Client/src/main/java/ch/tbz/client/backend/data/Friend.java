package ch.tbz.client.backend.data;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Friend extends Person implements Chat {
    private final String state; //accepted, send, received
    private final ArrayList<Message> messages;
    private int newMessages;
    private boolean isOn;

    public Friend(int userId, String name, String state, ArrayList<Message> messages){
        super(userId, name);
        this.state = state;
        this.messages = messages;
    }

    public void accept(){
        if (state.equals("received")){

        }
    }

    public String getState() {
        return state;
    }

    public boolean isOn() {
        return isOn;
    }

    public int getNewMessages() {
        return newMessages;
    }

    @Override
    public ArrayList<Message> getMessage() {
        return this.messages;
    }
}
