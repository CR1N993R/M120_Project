package ch.tbz.client.frontend.controller._prefaps;

import ch.tbz.client.Main;
import ch.tbz.client.backend.util.DataProperties;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;

public class SettingsController {

    public ToggleButton fullscreenTB;
    public ToggleButton darkmodeTB;

    public void init(){
        this.fullscreenTB.setSelected(DataProperties.isFullscreen());
        this.fullscreenTB.setText(DataProperties.isFullscreen() ? "On" : "Off");
        this.darkmodeTB.setStyle(DataProperties.isFullscreen() ? "-fx-background-color: #73fc03" : "-fx-background-color: red");

        this.darkmodeTB.setSelected(DataProperties.isDarkmode());
        this.darkmodeTB.setText(DataProperties.isDarkmode() ? "On" : "Off");
        this.darkmodeTB.setStyle(DataProperties.isDarkmode() ? "-fx-background-color: #73fc03" : "-fx-background-color: red");
    }

    public void toggleDarkMode(ActionEvent actionEvent) {
        DataProperties.setIsDarkmode(!DataProperties.isDarkmode());
        init();
    }

    public void toggleFullscreen(ActionEvent actionEvent) {
        DataProperties.setIsFullscreen(!DataProperties.isFullscreen());
        init();
    }

    public void applyPressed(ActionEvent actionEvent) {
        new Main().load();
    }
}
