module com.example.foodcravings {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.management;
    requires java.desktop;


    opens com.example.foodcravings to javafx.fxml;
    exports com.example.foodcravings;
    exports com.example.foodcravings.controller;
    opens com.example.foodcravings.controller to javafx.fxml;
    exports com.example.foodcravings.model;
    opens com.example.foodcravings.model to javafx.fxml;
}