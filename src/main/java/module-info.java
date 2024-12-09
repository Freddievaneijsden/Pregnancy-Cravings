module com.example.foodcravings {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.management;


    opens com.example.foodcravings to javafx.fxml;
    exports com.example.foodcravings;
    exports com.example.foodcravings.controller;
    opens com.example.foodcravings.controller to javafx.fxml;
}