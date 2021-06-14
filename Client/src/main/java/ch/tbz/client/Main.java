package ch.tbz.client;

import ch.fenix.clientwrapper.Connection;
import ch.tbz.client.frontend.UIManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        new Data();
        UIManager.primaryStage = primaryStage;
        UIManager.secondaryStage = new Stage();
        UIManager.login();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
