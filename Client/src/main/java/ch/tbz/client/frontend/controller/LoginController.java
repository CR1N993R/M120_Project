package ch.tbz.client.frontend.controller;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.frontend.UIManager;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.Locale;

public class LoginController extends ControllerBase {
    public TextField emailTb;
    public PasswordField pwdTb;
    public Label label;

    public void init() {
        UIManager.primaryStage.getScene().setOnKeyPressed((e) -> {
            if (e.getCode().toString().equals("ENTER")){
                clicked();
            }
        });
    }

    public void forgotPasswordClicked() {
    }

    public void registerClicked() {
        UIManager.register();
    }

    public void clicked() {
        Socket.login(emailTb.getText(), pwdTb.getText(), (s)->{
            if(s.equals("Success!")){
                UIManager.home();
            }else {
                this.label.setText(s);
            }
        });
    }
}
