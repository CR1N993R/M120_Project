package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.backend.data.Message;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MessageController {
    public Label messageLabel;
    public Label senderUsernameLabel;
    public Label dateLabel;
    public AnchorPane messagePane;
    public Tooltip senderUsernameTooltip;

    public void init(Message message){
        this.messageLabel.setText(message.getMessage());
        this.senderUsernameLabel.setText(message.getSender().getUsername());
        this.senderUsernameTooltip.setText(message.getSender().getUsername());
        if (message.getSentAt().toLocalDate().equals(LocalDate.now())){
            this.dateLabel.setText(message.getSentAt().format(DateTimeFormatter.ofPattern("HH:mm")));
        }else{
            this.dateLabel.setText(message.getSentAt().format(DateTimeFormatter.ofPattern("HH:mm d.M.y")));
        }
    }
}
