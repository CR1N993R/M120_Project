package ch.tbz.client.frontend.controller;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.data.*;
import ch.tbz.client.backend.interfaces.Chat;
import ch.tbz.client.frontend.UIManager;
import ch.tbz.client.frontend.controller.prefabs.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController extends ControllerBase {
    public VBox vboxServer;
    public Label usernameLabel;
    public Label uniqueIdLabel;
    public VBox vboxFriends;
    public AnchorPane chat;
    public Tooltip tooltipAddServer;
    public Tooltip tooltip;
    private User user;
    private Group group;
    private Friend friend;

    public void baseInit(User user) {
        this.user = user;
        this.usernameLabel.setText(this.user.getUsername());
        this.uniqueIdLabel.setText(this.user.getUserId() + "");
        tooltipAddServer.setText("New Groupchat");
        tooltip.setText("Show Chats with friends");
        initServers();
    }

    public void init(User user) {
        Socket.addGetDataListener(this::reload);
        baseInit(user);
        initFriends();
    }

    public void init(User user, Friend friend) {
        Socket.addGetDataListener(this::reload);
        this.friend = friend;
        baseInit(user);
        initFriends();
        initChat(friend);
    }

    public void init(User user, Group group) {
        Socket.addGetDataListener(this::reload);
        this.group = group;
        baseInit(user);
        initServerBar(group);
        initChat(group);
    }

    private void initServerBar(Group group) {
        vboxFriends.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(TextChannelController.class.getResource("views/prefabs/textChannel.fxml"));
            Parent root = loader.load();
            TextChannelController controller = loader.getController();
            controller.init(group);
            vboxFriends.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initChat(Chat chatMessage) {
        chat.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(ChatController.class.getClassLoader().getResource("views/prefabs/chat.fxml"));
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
            FXMLLoader loader = new FXMLLoader(FriendsBarController.class.getClassLoader().getResource("views/prefabs/friendsBar.fxml"));
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
                FXMLLoader loader = new FXMLLoader(GroupIconController.class.getClassLoader().getResource("views/prefabs/groupIcon.fxml"));
                Parent root = loader.load();
                GroupIconController controller = loader.getController();
                controller.init(group);
                vboxServer.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void settingsClicked() {
        UIManager.settings();
    }

    public void friendsClicked() {
        UIManager.home();
    }

    public void addGroupClicked() {
        UIManager.addGroup();
    }

    public void reload() {
        baseInit(user);
        if (group != null) {
            initServerBar(group);
            initChat(group);
        } else if (friend != null) {
            initFriends();
            initChat(friend);
        }else {
            initFriends();
        }
    }
}
