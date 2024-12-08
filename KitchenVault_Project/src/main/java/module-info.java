module dbms.project.kitchenvault_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens dbms.project.kitchenvault_project to javafx.fxml;
    exports dbms.project.kitchenvault_project;
    exports dbms.project.kitchenvault_project.controllers;
    opens dbms.project.kitchenvault_project.controllers to javafx.fxml;
    opens dbms.project.kitchenvault_project.classes to javafx.base;
}