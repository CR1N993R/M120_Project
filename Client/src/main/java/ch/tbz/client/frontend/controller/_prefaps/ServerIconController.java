package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.backend.data.Group;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class ServerIconController {
    public Button serverButton;
    public Tooltip tooltip;
    private Group group;

    public void init(Group group){
        this.group = group;
        serverButton.setText(this.group.getGroupName().substring(0, 1));
        tooltip.setText(this.group.getGroupName());
    }

    public void clicked(ActionEvent actionEvent) {

    }
}
