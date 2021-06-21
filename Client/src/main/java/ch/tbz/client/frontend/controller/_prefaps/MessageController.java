package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.backend.data.Message;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MessageController {
    public Label messageLabel;
    public Label senderUsernameLabel;
    public Label dateLabel;
    public Message message;

    public void init(Message message){
        this.message = message;
        this.messageLabel.setText(this.message.getMessage());
        this.senderUsernameLabel.setText(this.message.getSender().getUsername());
        if (this.message.getSentAt().toLocalDate().equals(LocalDate.now())){
            this.dateLabel.setText(this.message.getSentAt().format(DateTimeFormatter.ofPattern("HH:mm")));
        }else{
            this.dateLabel.setText(this.message.getSentAt().format(DateTimeFormatter.ofPattern("HH:mm d.M.y")));
        }
    }
}
