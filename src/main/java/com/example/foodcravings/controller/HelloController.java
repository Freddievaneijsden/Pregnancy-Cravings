package com.example.foodcravings.controller;

import com.example.foodcravings.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label buttonOne;

    @FXML
    private Label buttonTwo;

    @FXML
    protected void onButtonOneClick() {
        buttonOne.setText(Food.selectFood("SELECT * FROM mat"));

    }


    public void onButtonTwoClick() {
        buttonTwo.setText("Welcome to JavaFX Application!!");
    }
}