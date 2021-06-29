package ch.tbz.client.backend.interfaces;

import ch.tbz.client.backend.data.Message;

import java.util.ArrayList;

public interface Chat {
    public ArrayList<Message> getMessages();
}
