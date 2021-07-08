package ch.tbz.client.frontend.controller.prefabs;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.frontend.controller.ControllerBase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RequestsController extends ControllerBase {
    public VBox friendRequestVbox;

    public void init(){
        friendRequestVbox.getChildren().clear();
        for (Friend friend : Socket.getUser().getFriends()) {
            if (friend.getState().equals("received")) {
                try {
                    FXMLLoader loader = new FXMLLoader(FriendRequestController.class.getClassLoader().getResource("views/prefabs/friendRequest.fxml"));
                    Parent root = loader.load();
                    FriendRequestController controller = loader.getController();
                    controller.init(friend);
                    friendRequestVbox.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
