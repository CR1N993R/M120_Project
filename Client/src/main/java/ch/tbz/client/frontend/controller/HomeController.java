package ch.tbz.client.frontend.controller;

import ch.tbz.client.backend.data.*;
import ch.tbz.client.frontend.controller._prefaps.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController {
    public VBox vboxServer;
    public Label usernameLabel;
    public Label uniqueIdLabel;
    public VBox vboxFriends;
    public AnchorPane chat;
    public Tooltip tooltipAddServer;
    public Tooltip tooltip;
    private User user;

    public void init(User user) {
        this.user = user;
        this.usernameLabel.setText(this.user.getUsername());
        this.uniqueIdLabel.setText(this.user.getUserId() + "");
        tooltipAddServer.setText("New Groupchat");
        tooltip.setText("Show Chats with friends");
        initServers();
        initFriends();
    }

    public void init(User user, Friend friend) {
        this.user = user;
        this.usernameLabel.setText(this.user.getUsername());
        this.uniqueIdLabel.setText(this.user.getUserId() + "");
        tooltipAddServer.setText("New Groupchat");
        tooltip.setText("Show Chats with friends");
        initServers();
        initFriends();
        initChat(friend);
    }

    public void init(User user, Group group) {
        this.user = user;
        this.usernameLabel.setText(this.user.getUsername());
        this.uniqueIdLabel.setText(this.user.getUserId() + "");
        tooltipAddServer.setText("New Groupchat");
        tooltip.setText("Show Chats with friends");
        initServers();
        initServerBar();
        initChat(group);
    }

    private void initServerBar() {

    }

    private void initChat(Chat chatMessage) {
        chat.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(ChatController.class.getResource("chat.fxml"));
            Parent root = loader.load();
            ChatController controller = loader.getController();
            controller.init(chatMessage);
            chat.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initFriends() {
        vboxFriends.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(FriendsBarController.class.getClassLoader().getResource("friendsBar.fxml"));
            Parent root = loader.load();
            FriendsBarController controller = loader.getController();
            controller.init(user);
            vboxFriends.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initServers() {
        vboxServer.getChildren().clear();
        for (Group group : user.getGroups()) {
            try {
                FXMLLoader loader = new FXMLLoader(ServerIconController.class.getClassLoader().getResource("serverIcon.fxml"));
                Parent root = loader.load();
                ServerIconController controller = loader.getController();
                controller.init(group);
                vboxServer.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void settingsClicked(MouseEvent mouseEvent) {
    }

    public void friendsClicked(ActionEvent actionEvent) {
    }
}
