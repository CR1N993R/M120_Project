package ch.tbz.client.frontend.controller;

import ch.tbz.client.Main;
import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.frontend.UIManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddFriendController {
    public TextField idTF;
    public Label errorMessageLabel;

    public void init(){
        idTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    idTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    public void send() {
        Socket.getUser().sendFriendRequest(Integer.parseInt(idTF.getText()));
        new Main().load();
    }
}
