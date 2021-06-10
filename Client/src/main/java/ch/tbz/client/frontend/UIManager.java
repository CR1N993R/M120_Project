package ch.tbz.client.frontend;

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

    public static ControllerBase loadScene(Stage stage, String path, String title) {
        FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource(path));
        try {
            Parent root = loader.load();
            stage.setTitle(title);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException ignored){}
        return loader.getController();
    }

    public static void login(){

    }
}
