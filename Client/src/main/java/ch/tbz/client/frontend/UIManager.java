package ch.tbz.client.frontend;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.backend.data.Group;
import ch.tbz.client.backend.util.DataProperties;
import ch.tbz.client.frontend.controller.ControllerBase;
import ch.tbz.client.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UIManager {
    public static Stage primaryStage;
    public static Stage secondaryStage;

    private static ControllerBase loadScene(Stage stage, String path, String title) {
        FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource(path));
        try {
            Parent root = loader.load();
            stage.setTitle(title);
            Scene scene = new Scene(root);
            if (DataProperties.isDarkmode()) {
                scene.getStylesheets().add("darkmode.css");
            }
            stage.setScene(scene);
            stage.show();
        } catch (IOException ignored) {
        }
        return loader.getController();
    }

    public static void login() {
        Socket.clearGetDataListeners();
        loadScene(primaryStage, "views/login.fxml", "Login").init();
    }

    public static void register() {
        Socket.clearGetDataListeners();
        loadScene(primaryStage, "views/register.fxml", "Register").init();
    }

    public static void home() {
        Socket.clearGetDataListeners();
        loadScene(primaryStage, "views/home.fxml", "Home").init();
    }

    public static void home(Friend friend) {
        Socket.clearGetDataListeners();
        loadScene(primaryStage, "views/home.fxml", "Home").init(friend);
    }

    public static void home(Group group) {
        Socket.clearGetDataListeners();
        loadScene(primaryStage, "views/home.fxml", "Home").init(group);
    }

    public static void addFriend() {
        loadScene(secondaryStage, "views/addFriends.fxml", "Add Friends").init();
    }

    public static void settings() {
        loadScene(secondaryStage, "views/menu.fxml", "Menu").init();
    }

    public static void addGroup() {
        loadScene(secondaryStage, "views/newGroupchat.fxml", "Add Groupchat");
    }

    public static void close() {
        primaryStage.close();
        secondaryStage.close();
    }

    public static void closeSecondary() {
        secondaryStage.close();
    }
}
