package dbms.project.kitchenvault_project.controllers;

import dbms.project.kitchenvault_project.classes.DatabaseTalk;
import dbms.project.kitchenvault_project.classes.RowData;
import dbms.project.kitchenvault_project.classes.SceneMaster;
import dbms.project.kitchenvault_project.classes.fieldMatters;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class productOutController {

    // for take
    @FXML private TextField id1;
    @FXML private TextField quantity;

    @FXML private Button takeByQ;
    @FXML private Button takeAll;

    // for display
    @FXML
    private Button productInBtn;

    @FXML
    private Button exitBtn;

    @FXML Button searchBtn;
    @FXML Button clearBtn;

    @FXML private TextField id;
    @FXML private TextField pr_name;
    @FXML private ComboBox<String> type;
    @FXML private ComboBox<String> ep_mm;
    @FXML private ComboBox<String> ep_dd;
    @FXML private ComboBox<String> ep_yyyy;
    @FXML private ComboBox<String> sd_mm;
    @FXML private ComboBox<String> sd_dd;
    @FXML private ComboBox<String> sd_yyyy;

    @FXML private TableView<RowData> tableView;

    @FXML
    private TableColumn<RowData, Integer> idColumn;
    @FXML
    private TableColumn<RowData, String> pnColumn;
    @FXML
    private TableColumn<RowData, String> typeColumn;
    @FXML
    private TableColumn<RowData, Integer> quantityColumn;
    @FXML
    private TableColumn<RowData, String> costColumn;
    @FXML
    private TableColumn<RowData, String> tcColumn;
    @FXML
    private TableColumn<RowData, String> edColumn;
    @FXML
    private TableColumn<RowData, String> sdColumn;

    // database connection thingiez
    private static Connection connection;
    private static DatabaseTalk operate;

    public static void getDbmsConnection(Connection c) throws SQLException {
        connection = c;
        operate = new DatabaseTalk(c);
    }

    @FXML
    public void initialize(){
        type.getItems().addAll(fieldMatters.getTypes());
        ep_mm.getItems().addAll(fieldMatters.getMonths());
        sd_mm.getItems().addAll(fieldMatters.getMonths());
        ep_dd.getItems().addAll(fieldMatters.getDays());
        sd_dd.getItems().addAll(fieldMatters.getDays());
        ep_yyyy.getItems().addAll(fieldMatters.getYears());
        sd_yyyy.getItems().addAll(fieldMatters.getYears());

        // Bind table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        pnColumn.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        tcColumn.setCellValueFactory(new PropertyValueFactory<>("total_cost"));
        edColumn.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));
        sdColumn.setCellValueFactory(new PropertyValueFactory<>("stock_date"));
    }

    @FXML
    public void presentInTable() throws SQLException {
        ObservableList<RowData> data = FXCollections.observableArrayList();
        ResultSet rs = operate.searchDb(id.getText(), pr_name.getText(), type.getValue(), fieldMatters.validateNFormatDate(ep_mm.getValue(), ep_dd.getValue(), ep_yyyy.getValue()), fieldMatters.validateNFormatDate(sd_mm.getValue(), sd_dd.getValue(), sd_yyyy.getValue()));

        while(rs.next()){
            data.add(new RowData(
                    rs.getInt("id"),
                    rs.getString("product_name"),
                    rs.getString("type"),
                    rs.getInt("quantity"),
                    rs.getString("cost"),
                    rs.getString("total_cost"),
                    rs.getString("expiry_date"),
                    rs.getString("stock_date")
                    )
            );
        }

        tableView.setItems(data);
    }

    @FXML
    public void clearFilters(){
        id.setText("");
        pr_name.setText("");
        type.setValue(null);
        ep_dd.setValue(null);
        ep_mm.setValue(null);
        ep_yyyy.setValue(null);
        sd_mm.setValue(null);
        sd_dd.setValue(null);
        sd_yyyy.setValue(null);
    }

    @FXML
    public void goToProductIn() throws IOException, SQLException {
        SceneMaster.loadSceneIfNeeded("delivery1");
        SceneMaster.displayScene();
    }

    @FXML
    public void goToIntro() throws IOException, SQLException {
        SceneMaster.loadSceneIfNeeded("intro");
        SceneMaster.displayScene();
    }

    @FXML
    public void takingAll() throws SQLException {
        operate.deleteData(id1.getText());
    }

    @FXML
    public void takeByQuantity() throws SQLException {
        operate.deleteByQuantity(id1.getText(), Integer.parseInt(quantity.getText()));
    }
}
