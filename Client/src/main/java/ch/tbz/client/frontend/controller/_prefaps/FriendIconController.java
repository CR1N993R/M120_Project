package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.backend.data.Group;
import ch.tbz.client.frontend.UIManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class FriendIconController {
    public Label usernameLabel;
    public Tooltip usernameTooltip;
    public Circle statusCircle;
    public Label newMassageCount;
    public Friend friend;
    public Menu menu;

    public void init(Friend friend){
        this.friend = friend;
        this.usernameLabel.setText(this.friend.getUsername());
        this.usernameTooltip.setText(this.friend.getUsername());
        this.newMassageCount.setText(this.friend.getUnreadMessages() + "");
        this.statusCircle.setFill(this.friend.isOn() ? Color.GREEN : Paint.valueOf("#8F979F"));

        for (Group group : Socket.getUser().getGroups()){
            MenuItem item = new MenuItem(group.getGroupName());
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    group.addUserToGroup(friend.getUserId());
                }
            });
        }
    }

    public void setNewMassageCount(String text){
        this.newMassageCount.setText(text);
    }

    public void setStatusCircleON(){
        this.statusCircle.setFill(Color.GREEN);
    }

    public void setStatusCircleOFF(){
        this.statusCircle.setFill(Paint.valueOf("#8F979F"));
    }

    public void iconClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            UIManager.home(friend);
        }
    }
}
