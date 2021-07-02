package ch.tbz.client.frontend.controller;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.frontend.UIManager;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {
    public TextField emailTb;
    public PasswordField pwdTb;
    public Label label;

    public void forgotPasswordClicked(MouseEvent mouseEvent) {
    }

    public void registerClicked(MouseEvent mouseEvent) {
        UIManager.register();
    }

    public void clicked(ActionEvent actionEvent) {
        Socket.login(emailTb.getText(), pwdTb.getText(), (s)->{
            if(s.equals("Success!")){
                UIManager.home();
            }else {
                this.label.setText(s);
            }
        });
    }
}
