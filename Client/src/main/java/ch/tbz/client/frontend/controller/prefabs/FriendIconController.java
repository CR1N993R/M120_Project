package ch.tbz.client.frontend.controller.prefabs;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.backend.data.Group;
import ch.tbz.client.frontend.UIManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    public Menu menu2;
    public Menu menu3;

    public void init(Friend friend){
        this.friend = friend;
        this.usernameLabel.setText(this.friend.getUsername());
        this.usernameTooltip.setText(this.friend.getUsername());
        if (this.friend.getUnreadMessages() > 0){
            this.newMassageCount.setText(friend.getUnreadMessages() + "");
            if (this.friend.getUnreadMessages() > 9){
                this.newMassageCount.setText("9+");
            }
        }else{
            this.newMassageCount.setText("");
        }
        this.statusCircle.setFill(this.friend.isOnline() ? Color.GREEN : Paint.valueOf("#8F979F"));

        for (Group group : Socket.getUser().getGroups()){
            MenuItem item = new MenuItem(group.getGroupName());
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    group.addUserToGroup(friend.getUserId());
                }
            });
            menu.getItems().add(item);

            MenuItem item2 = new MenuItem(group.getGroupName());
            item2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    group.addUserToGroup(friend.getUserId());
                }
            });
            menu2.getItems().add(item2);

            MenuItem item3 = new MenuItem(group.getGroupName());
            item3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    group.addUserToGroup(friend.getUserId());
                }
            });
            menu3.getItems().add(item3);
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
