package ch.tbz.client.frontend.controller;

import ch.tbz.client.backend.data.*;
import ch.tbz.client.backend.interfaces.Chat;
import ch.tbz.client.frontend.controller._prefaps.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
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
    private User user;

    public void init(User user) {
        this.user = user;
        this.usernameLabel.setText(this.user.getUsername());
        this.uniqueIdLabel.setText(this.user.getUserId() + "");
        initServers();
        initFriends();
    }

    public void init(User user, Friend friend) {
        this.user = user;
        this.usernameLabel.setText(this.user.getUsername());
        this.uniqueIdLabel.setText(this.user.getUserId() + "");
        initServers();
        initFriends();
        initChat(friend);
    }

    public void init(User user, Group group) {
        this.user = user;
        this.usernameLabel.setText(this.user.getUsername());
        this.uniqueIdLabel.setText(this.user.getUserId() + "");
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
        try {
            FXMLLoader loader = new FXMLLoader(ServerIconController.class.getClassLoader().getResource("serverIcon.fxml"));
            Parent root = loader.load();
            vboxServer.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
