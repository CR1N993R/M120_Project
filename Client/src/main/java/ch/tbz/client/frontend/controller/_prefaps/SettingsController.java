package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.Main;
import ch.tbz.client.backend.data.DataProperties;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;

public class SettingsController {

    public ToggleButton fullscreenTB;
    public ToggleButton darkmodeTB;

    public void init(){
        this.fullscreenTB.setSelected(DataProperties.isIsFullscreen());
        this.fullscreenTB.setText(DataProperties.isIsFullscreen() ? "On" : "Off");

        this.darkmodeTB.setSelected(DataProperties.isIsDarkmode());
        this.darkmodeTB.setText(DataProperties.isIsDarkmode() ? "On" : "Off");
    }

    public void toggleDarkMode(ActionEvent actionEvent) {
        DataProperties.setIsDarkmode(!DataProperties.isIsDarkmode());
    }

    public void toggleFullscreen(ActionEvent actionEvent) {
        DataProperties.setIsFullscreen(!DataProperties.isIsFullscreen());
    }

    public void applypressed(ActionEvent actionEvent) {
        new Main().load();
    }
}
