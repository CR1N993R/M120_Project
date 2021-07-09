package ch.tbz.client.frontend.controller.prefabs;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.backend.data.User;
import ch.tbz.client.frontend.UIManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FriendsBarController {
    public TextField search;
    public VBox friends;

    public void init(String search){
        friends.getChildren().clear();
        for (Friend friend : Socket.getUser().getFriends()) {
            try {
                if (friend.getState().equals("accepted") && friend.getUsername().contains(search)) {
                    FXMLLoader loader = new FXMLLoader(FriendIconController.class.getClassLoader().getResource("views/prefabs/friendIcon.fxml"));
                    Parent root = loader.load();
                    FriendIconController controller = loader.getController();
                    controller.init(friend);
                    friends.getChildren().add(root);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addfriendClicked(MouseEvent mouseEvent) {
        UIManager.addFriend();
    }

    public void search(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER){
            init(this.search.getText());
        }
    }
}
