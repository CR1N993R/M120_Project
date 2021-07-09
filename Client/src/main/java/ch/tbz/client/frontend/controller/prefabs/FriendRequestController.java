package ch.tbz.client.frontend.controller.prefabs;

import ch.tbz.client.backend.data.Friend;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class FriendRequestController {
    public Label usernameLabel;
    public Friend friend;

    public void init(Friend friend){
        this.friend = friend;
        this.usernameLabel.setText(friend.getUsername());
    }

    public void declinedClicked() {
        friend.declineFriendRequest();
    }

    public void acceptClicked() {
        friend.acceptFriendRequest();
    }
}
