package dbms.project.kitchenvault_project;

import dbms.project.kitchenvault_project.classes.DatabaseConnector;
import dbms.project.kitchenvault_project.classes.SceneMaster;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {

        SceneMaster master = new SceneMaster(stage, DatabaseConnector.connect());
        SceneMaster.loadSceneIfNeeded("intro");
        SceneMaster.displayScene();
    }

    public static void main(String[] args) {
        launch();
    }
}