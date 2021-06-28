package ch.tbz.client.backend.data;

import ch.tbz.client.backend.connection.Socket;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class Group implements Chat {
    private final long groupId;
    private final String groupName;
    private final int unreadMessages;
    private final ArrayList<Person> users;
    private final ArrayList<Message> messages;

    public void removeUserFromGroup(long userId) {
        Socket.emit("removeUserFromGroup", "{\"userid\":\"" + userId + "\", \"groupId\":\""+ groupId + "\"}");
    }

    public void groupMessagesRead() {
        Socket.emit("groupMessagesRead", "{\"groupId\":\"" + groupId + "\"}");
    }

    public void leaveGroup () {
        Socket.emit("leaveGroup", "{\"groupId\":\"" + groupId + "\"}");
    }

    public void addUserToGroup(long userId) {
        Socket.emit("removeUserFromGroup", "{\"userid\":\"" + userId + "\", \"groupId\":\""+ groupId + "\"}");
    }

    public void changeGroupName(String name) {
        Socket.emit("removeUserFromGroup", "{\"groupName\":\"" + name + "\", \"groupId\":\""+ groupId + "\"}");
    }

    public void sendMessage(String msg) {
        Socket.emit("removeUserFromGroup", "{\"msg\":\"" + msg + "\", \"groupId\":\""+ groupId + "\"}");
    }
}
