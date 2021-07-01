package ch.tbz.client.backend.interfaces;

import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.backend.data.Group;
import ch.tbz.client.backend.data.Message;

import java.util.ArrayList;

public interface Chat {
    public ArrayList<Message> getMessages();

    public Friend getFriend();

    public Group getGroup();
}
