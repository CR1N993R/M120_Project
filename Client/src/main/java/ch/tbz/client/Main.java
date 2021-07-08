package ch.tbz.client;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.util.DataProperties;
import ch.tbz.client.frontend.UIManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public void load() {
        Socket.init();
        UIManager.close();
        UIManager.home();
    }

    @Override
    public void start(Stage primaryStage) {
        Socket.init();
        DataProperties.loadData();
        primaryStage.show();
        primaryStage.setOnCloseRequest((s) -> {
            Socket.close();
        });
        UIManager.primaryStage = primaryStage;
        UIManager.secondaryStage = new Stage();
        UIManager.login();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
