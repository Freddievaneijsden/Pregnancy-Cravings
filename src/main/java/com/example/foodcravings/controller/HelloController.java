package com.example.foodcravings.controller;

import com.example.foodcravings.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class HelloController {
    @FXML
    private Label buttonOne;

    @FXML
    private Label buttonTwo;

    @FXML
    protected void onButtonOneClick() {
        List<String> tempList = Food.selectFood("SELECT * FROM mat");
        StringBuilder combinedText = new StringBuilder();

        // If the list is empty, show a message
        if (tempList.isEmpty()) {
            buttonOne.setText("Ingen mat i databasen!");
            return;
        }

        combinedText.append("\uD83C\uDF7D️ Food Cravings: \n");
        for (int i = 0; i < tempList.size(); i++) {
            combinedText.append(i + 1).append(". ").append(tempList.get(i)).append("\n"); // Number each item
        }

        buttonOne.setText(combinedText.toString());
    }


    public void onButtonTwoClick() {
        buttonTwo.setText("Welcome to JavaFX Application!!");
    }
}