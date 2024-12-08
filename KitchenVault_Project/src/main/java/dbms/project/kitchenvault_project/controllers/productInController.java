package dbms.project.kitchenvault_project.controllers;

import dbms.project.kitchenvault_project.classes.DatabaseTalk;
import dbms.project.kitchenvault_project.classes.SceneMaster;
import dbms.project.kitchenvault_project.classes.fieldMatters;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class productInController {
    @FXML private Button productOutBtn;

    @FXML private Button exitBtn;

    @FXML private Button add;

    @FXML private Button clear;

    @FXML private Text warning;

    @FXML private ComboBox<String> type;

    @FXML private ComboBox<String> mm;

    @FXML private ComboBox<String> dd;

    @FXML private ComboBox<String> yyyy;

    @FXML private TextField name;

    @FXML private TextField quantity;

    @FXML private TextField price;

    private static DatabaseTalk operate;

    int count = 5;

    public static void getDbmsConnection(Connection c) throws SQLException {
        operate = new DatabaseTalk(c);
    }

    @FXML
    public void initialize(){
        // populating combo boxes
        type.getItems().addAll(fieldMatters.getTypes());
        mm.getItems().addAll(fieldMatters.getMonths());
        dd.getItems().addAll(fieldMatters.getDays());
        yyyy.getItems().addAll(fieldMatters.getYears());
    }

    @FXML
    public void goToProductOut() throws IOException, SQLException {
        SceneMaster.loadSceneIfNeeded("delivery2");
        SceneMaster.displayScene();
    }

    @FXML
    public void goToIntro() throws IOException, SQLException {
        SceneMaster.loadSceneIfNeeded("intro");
        SceneMaster.displayScene();
    }

    @FXML
    public void addToDb() throws SQLException {
        if(!fieldMatters.canConvertToInt(quantity.getText()) || !fieldMatters.canConvertToDouble(price.getText()) && !fieldMatters.allInputIsFilled(name.getText(), type.getValue(), quantity.getText(), price.getText(), mm.getValue(), dd.getValue(), yyyy.getValue())){
            warning.setVisible(true);
        } else{
            warning.setVisible(false);
            // get proper format for expiry date
            String expiry_date = mm.getValue() + "/" + dd.getValue() + "/" + yyyy.getValue();
            // add all to database
            operate.addDataToDb(name.getText(), type.getValue(), Integer.parseInt(quantity.getText()), Double.parseDouble(price.getText()), expiry_date);
        }
    }

    @FXML
    public void clearInputs(){
        name.setText("");
        quantity.setText("");
        type.setValue(null);
        price.setText("");
        mm.setValue(null);
        dd.setValue(null);
        yyyy.setValue(null);
    }
}
