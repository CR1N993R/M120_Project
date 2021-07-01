package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.backend.data.Group;
import ch.tbz.client.backend.data.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TextChannelController {
   public Label channelName;
   public VBox members;
    public Tooltip tooltip;

    public void init(Group group){
      this.channelName.setText(group.getGroupName());
      this.tooltip.setText(group.getGroupName());
      members.getChildren().clear();
      for (Person person : group.getUsers()) {
         Label label = new Label(person.getUsername());
         members.getChildren().add(label);
      }
   }
}
