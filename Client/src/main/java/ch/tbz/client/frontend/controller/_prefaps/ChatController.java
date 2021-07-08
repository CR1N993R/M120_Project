package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.backend.data.Group;
import ch.tbz.client.backend.data.Message;
import ch.tbz.client.backend.interfaces.Chat;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ChatController {
    public VBox vboxMessages;
    public TextField messageTb;
    public ArrayList<Message> messages;
    public Friend friend;
    public Group group;
    public String type;

    public void init(Chat chatMessages){
        this.messages = chatMessages.getMessages();
        if (chatMessages instanceof Friend){
            type = "friend";
            this.friend = (Friend) chatMessages;
        }else {
            type = "group";
            this.group = (Group) chatMessages;
        }

        vboxMessages.getChildren().clear();
        for(Message message : messages){
            try {
                FXMLLoader loader = new FXMLLoader(MessageController.class.getClassLoader().getResource("views/_prefaps/message.fxml"));
                Parent root = loader.load();
                MessageController controller = loader.getController();
                controller.init(message);
                vboxMessages.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendClicked() {
        String text = this.messageTb.getText();
        text = text.replace("/shrug", "¯\\_(ツ)_/¯").replace("/tableflip", "(╯°□°）╯︵ ┻━┻").replace("/unflip", "┬─┬ ノ( ゜-゜ノ)");
        switch (type){
            case "group":
                this.group.sendMessage(text);
                break;
            case "friend":
                this.friend.sendToFriend(text);
                break;
        }
    }
}
