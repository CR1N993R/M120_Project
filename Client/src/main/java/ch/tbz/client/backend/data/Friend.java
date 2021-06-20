package ch.tbz.client.backend.data;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Friend extends Person {
    private final String state;
    private final ArrayList<Message> messages;

    public Friend(int userId, String name, String state, ArrayList<Message> messages){
        super(userId, name);
        this.state = state;
        this.messages = messages;
    }

    public void accept(){
        if (state.equals("received")){

        }
    }
}
