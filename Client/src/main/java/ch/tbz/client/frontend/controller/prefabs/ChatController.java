package ch.tbz.client.frontend.controller.prefabs;

import ch.tbz.client.backend.data.Friend;
import ch.tbz.client.backend.data.Group;
import ch.tbz.client.backend.data.Message;
import ch.tbz.client.backend.interfaces.Chat;
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
        messageTb.setOnKeyPressed((e) -> {
            if (messageTb.isFocused() && e.getCode().toString().equals("ENTER")){
                send(messageTb.getText());
            }
        });
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
                FXMLLoader loader = new FXMLLoader(MessageController.class.getClassLoader().getResource("views/prefabs/message.fxml"));
                Parent root = loader.load();
                MessageController controller = loader.getController();
                controller.init(message);
                vboxMessages.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String text){
        text = text.replace("/shrug", "¯\\_(ツ)_/¯").replace("/tableflip", "(╯°□°）╯︵ ┻━┻").replace("/unflip", "┬─┬ ノ( ゜-゜ノ)");
        switch (type) {
            case "group" -> {
                this.group.sendMessage(text);
            }
            case "friend" -> {
                this.friend.sendToFriend(text);
            }
        }
    }

    public void sendClicked() {
        String text = this.messageTb.getText();
        send(text);
    }
}
