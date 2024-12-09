module com.example.foodcravings {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.foodcravings to javafx.fxml;
    exports com.example.foodcravings;
    exports com.example.foodcravings.controller;
    opens com.example.foodcravings.controller to javafx.fxml;
}