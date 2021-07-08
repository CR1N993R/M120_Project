package ch.tbz.client.frontend.controller.prefabs;

import ch.tbz.client.Main;
import ch.tbz.client.backend.util.DataProperties;
import ch.tbz.client.frontend.controller.ControllerBase;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;

public class SettingsController extends ControllerBase {

    public ToggleButton fullscreenTB;
    public ToggleButton darkmodeTB;

    public void init(){

        this.darkmodeTB.setSelected(DataProperties.isDarkmode());
        this.darkmodeTB.setText(DataProperties.isDarkmode() ? "On" : "Off");
        this.darkmodeTB.setStyle(DataProperties.isDarkmode() ? "-fx-background-color: #73fc03" : "-fx-background-color: red");
    }

    public void toggleDarkMode(ActionEvent actionEvent) {
        DataProperties.setIsDarkmode(!DataProperties.isDarkmode());
        init();
    }

    public void applyPressed(ActionEvent actionEvent) {
        new Main().load();
    }
}
