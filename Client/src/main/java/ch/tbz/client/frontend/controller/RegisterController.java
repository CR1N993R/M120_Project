package ch.tbz.client.frontend.controller;

import ch.tbz.client.backend.connection.Socket;
import ch.tbz.client.backend.util.Validator;
import ch.tbz.client.frontend.UIManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegisterController extends ControllerBase{
    public TextField usernameTb;
    public PasswordField pwdTb;
    public Label errorMSG;

    public void haveAccountClicked(MouseEvent mouseEvent) {
        UIManager.login();
    }

    public void registerClicked(ActionEvent actionEvent) {
        String msg = Validator.validateRegister(usernameTb.getText(), pwdTb.getText());
        if (msg.equals("")) {
            Socket.register(usernameTb.getText(), pwdTb.getText(), (s) -> {
                if(s.equals("Success!")){
                    UIManager.login();
                }else {
                    errorMSG.setText(s);
                }
            });
        }else{
            errorMSG.setText(msg);
        }
    }
}
