package ch.tbz.client.frontend.controller;

import ch.tbz.client.frontend.controller._prefaps.FriendsController;
import ch.tbz.client.frontend.controller._prefaps.GroupIconController;
import ch.tbz.client.frontend.controller._prefaps.RequestsController;
import ch.tbz.client.frontend.controller._prefaps.SettingsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuController extends ControllerBase {
    public AnchorPane pane;

    public void init(){
        toSettings();
    }

    public void toSettings() {
        pane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(SettingsController.class.getClassLoader().getResource("views/_prefaps/settings.fxml"));
            Parent root = loader.load();
            SettingsController controller = loader.getController();
            controller.init();
            pane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void toFriends(ActionEvent actionEvent) {
        pane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(FriendsController.class.getClassLoader().getResource("views/_prefaps/friends.fxml"));
            Parent root = loader.load();
            FriendsController controller = loader.getController();
            controller.init();
            pane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void toRequests(ActionEvent actionEvent) {
        pane.getChildren().clear();
        try {
            FXMLLoader loader = new FXMLLoader(RequestsController.class.getClassLoader().getResource("views/_prefaps/requests.fxml"));
            Parent root = loader.load();
            RequestsController controller = loader.getController();
            controller.init();
            pane.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
