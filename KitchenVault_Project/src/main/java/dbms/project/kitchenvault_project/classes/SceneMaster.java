package dbms.project.kitchenvault_project.classes;

import dbms.project.kitchenvault_project.Main;
import dbms.project.kitchenvault_project.controllers.productInController;
import dbms.project.kitchenvault_project.controllers.productOutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class SceneMaster {

    private static Stage stage;
    private static final HashMap<String, Scene> scenes = new HashMap<>();
    private static final HashMap<String, String> sceneNames = new HashMap<>();
    private static String inQueue = "intro";

    private static Connection connection;

    public SceneMaster(Stage stager, Connection c) {

        sceneNames.put("intro", "index.fxml");
        sceneNames.put("delivery1", "Delivery(in).fxml");
        sceneNames.put("delivery2", "Delivery(out).fxml");

        stage = stager;
        connection = c;

        stage.setTitle("Kitchen Vault - Food product Inventory");
    }

    public static void loadSceneIfNeeded(String sceneName) throws IOException, SQLException {
        if(!scenes.containsKey(sceneName)){
            switch (sceneName){
                case "intro":
                    scenes.put(sceneName, new Scene(FXMLLoader.load(Main.class.getResource("/dbms/project/fxml.files/index.fxml"))));
                    break;

                case "delivery1":
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("/dbms/project/fxml.files/" + sceneNames.get(sceneName)));
                    // passing dbms connection
                    productInController.getDbmsConnection(connection);
                    scenes.put(sceneName, new Scene(loader.load()));
                    break;

                case "delivery2":
                    FXMLLoader loader2 = new FXMLLoader(Main.class.getResource("/dbms/project/fxml.files/" + sceneNames.get(sceneName)));
                    productOutController.getDbmsConnection(connection);
                    scenes.put(sceneName, new Scene(loader2.load()));
                    break;
            }
        }

        inQueue = sceneName;
    }

    public static void displayScene(){
        stage.setScene(scenes.get(inQueue));
        stage.show();
    }
}
