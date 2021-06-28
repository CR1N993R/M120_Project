package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.backend.data.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FriendsBarController {
    public TextField search;
    public VBox friends;

    public void init(User user){
        friends.getChildren().clear();
        for (Friend friend : user.getFriends()) {
            try {
                FXMLLoader loader = new FXMLLoader(FriendIconController.class.getClassLoader().getResource("friendIcon.fxml"));
                Parent root = loader.load();
                FriendIconController controller = loader.getController();
                controller.init(friend);
                friends.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addfriendClicked(MouseEvent mouseEvent) {
    }
}