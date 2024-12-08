package dbms.project.kitchenvault_project.controllers;

import dbms.project.kitchenvault_project.classes.SceneMaster;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;

public class introController {
    @FXML
    private Button adminBtn;

    @FXML
    private Button deliveryBtn;

    @FXML
    public void showDeliveryInterface() throws IOException, SQLException {
        SceneMaster.loadSceneIfNeeded("delivery1");
        SceneMaster.displayScene();
    }
}