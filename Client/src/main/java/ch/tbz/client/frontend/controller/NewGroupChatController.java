package ch.tbz.client.frontend.controller;

import ch.tbz.client.Main;
import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.frontend.UIManager;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class NewGroupChatController extends ControllerBase {
    public TextField name;

    public void createClicked(ActionEvent actionEvent) {
        Socket.addGetDataListener(()->{
            new Main().load();
        });
        Socket.getUser().createGroup(name.getText());
    }
}
