package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.backend.data.Friend;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FriendIconController {
    public Label usernameLabel;
    public Tooltip usernameTooltip;
    public Circle statusCircle;
    public Label newMassageCount;
    public Friend friend;

    public void init(Friend friend){
        this.friend = friend;
        this.usernameLabel.setText(this.friend.getUsername());
        this.usernameTooltip.setText(this.friend.getUsername());
        this.newMassageCount.setText(this.friend.getUnreadMessages() + "");
        this.statusCircle.setFill(this.friend.isOn() ? Color.GREEN : Color.RED);
    }
}
