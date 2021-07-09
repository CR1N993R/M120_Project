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
    public AnchorPane paneFriends;
    public AnchorPane chat;
    public Tooltip tooltipAddServer;
    public Tooltip tooltip;
    private long group;
    private long friend;

    public void baseInit() {
        this.usernameLabel.setText(Socket.getUser().getUsername());
        this.uniqueIdLabel.setText(Socket.getUser().getUserId() + "");
        tooltipAddServer.setText("New Groupchat");
        tooltip.setText("Show Chats with friends");
        initServers();
    }

    public void init() {
        Socket.addGetDataListener(this::reload);
        baseInit();
        initFriends();
    }

    public void init(Friend friend) {
        if (friend.getUnreadMessages() != 0) {
            friend.friendMessagesRead();
        }
        Socket.addGetDataListener(this::reload);
        this.friend = friend.getUserId();
        baseInit();
        initFriends();
        initChat(friend);
    }

    public void init(Group group) {
        if (group.getUnreadMessages() != 0) {
            group.groupMessagesRead();
        }
        Socket.addGetDataListener(this::reload);
        this.group = group.getGroupId();
        baseInit();
        initServerBar(group);
        initChat(group);
    }

    private void initServerBar(Group group) {
        paneFriends.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(TextChannelController.class.getClassLoader().getResource("views/prefabs/textChannel.fxml"));
            Parent root = loader.load();
            TextChannelController controller = loader.getController();
            controller.init(group);
            paneFriends.getChildren().add(root);
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
        paneFriends.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(FriendsBarController.class.getClassLoader().getResource("views/prefabs/friendsBar.fxml"));
            Parent root = loader.load();
            FriendsBarController controller = loader.getController();
            controller.init("");
            paneFriends.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initServers() {
        vboxServer.getChildren().clear();
        for (Group group : Socket.getUser().getGroups()) {
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
        baseInit();
        if (group != 0) {
            Group g = Socket.getUser().getGroupById(group);
            if (g.getUnreadMessages() != 0) {
                g.groupMessagesRead();
            }
            initServerBar(g);
            initChat(g);
        } else if (friend != 0) {
            Friend f = Socket.getUser().getUserById(friend);
            if (f.getUnreadMessages() != 0) {
                f.friendMessagesRead();
            }
            initFriends();
            initChat(f);
        }else {
            initFriends();
        }
    }
}
