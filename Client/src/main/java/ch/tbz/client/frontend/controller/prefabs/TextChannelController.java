package ch.tbz.client.frontend.controller.prefabs;

import ch.tbz.client.backend.data.Group;
import ch.tbz.client.backend.data.Person;
import ch.tbz.client.frontend.controller.ControllerBase;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

public class TextChannelController extends ControllerBase {
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
