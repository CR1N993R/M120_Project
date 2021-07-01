package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.frontend.UIManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FriendsController {
    public VBox friendsVbox;

    public void init(){
        friendsVbox.getChildren().clear();
        for (Friend friend : Socket.getUser().getFriends()) {
            try {
                FXMLLoader loader = new FXMLLoader(FriendIconController.class.getClassLoader().getResource("friendIcon.fxml"));
                Parent root = loader.load();
                FriendIconController controller = loader.getController();
                controller.init(friend);
                friendsVbox.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addFriendClicked(MouseEvent mouseEvent) {
        UIManager.addFriend();
    }
}
