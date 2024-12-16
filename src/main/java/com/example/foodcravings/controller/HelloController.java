package com.example.foodcravings.controller;

import com.example.foodcravings.model.DislikedFood;
import com.example.foodcravings.model.FavoriteFood;
import com.example.foodcravings.model.Food;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Optional;

public class HelloController {
    @FXML
    private VBox root;

    @FXML
    private ListView<String> foodListView;

    @FXML
    private TextInputDialog addFood;

    @FXML
    private TextInputDialog changeFavoriteFood;

    //En knapp för favoriter och en för icke favoriter
    //Inga siffror, hjärtan på favoriter
    //Mums och bläh
    //Bild på amanda som bakgrund

    @FXML
    private Label buttonTwo;

    public void initialize() {
        String imagePath = getClass().getResource("/com/example/foodcravings/image/Amanda.jpg").toExternalForm();
        root.setStyle("-fx-background-image: url('" + imagePath + "'); -fx-background-size: cover;");

    }

    public void onAddFoodButtonClick() {
        addFood = new TextInputDialog();
        addFood.setTitle("Lägg till");
        addFood.setHeaderText("Vilken mat vill du lägga till?");

        // Show the dialog and capture the input
        Optional<String> result = addFood.showAndWait();
        result.ifPresent(food -> {
            if (!food.isEmpty()) {
                // Optionally, save to database
                Food.insertNewtFood(food);
            }
            // Create a new dialog to ask if it's a favorite
            Dialog<ButtonType> favoriteDialog = new Dialog<>();
            favoriteDialog.setTitle("Mums");
            favoriteDialog.setHeaderText("Vill du markera detta som mums?");

            // Set the button types
            ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            favoriteDialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

            // Create a checkbox for the favorite option
            CheckBox favoriteCheckbox = new CheckBox("Ok");

            // Add the checkbox to the dialog pane
            VBox content = new VBox(favoriteCheckbox);
            content.setSpacing(10);
            favoriteDialog.getDialogPane().setContent(content);

            // Show the dialog and capture the result
            Optional<ButtonType> favoriteResult = favoriteDialog.showAndWait();
            favoriteResult.ifPresent(button -> {
                if (button == okButtonType) { // Check if the user clicked OK
                    if (favoriteCheckbox.isSelected()) { // Check if the checkbox is selected
                        // Handle favorite logic
                        FavoriteFood.changeToFavorite(food);
                    }
                }
            });
        });
    }

    public void onButtonOneClick() {
        List<String> tempList = Food.selectFood("SELECT * FROM mat");
        StringBuilder combinedText = new StringBuilder();

        // Clear existing items
        foodListView.getItems().clear();

        // If the list is empty, show a message
        if (tempList.isEmpty()) {
            foodListView.getItems().add("Ingen mat i databasen!");
            return;
        }

        // Add all items to the ListView
        foodListView.getItems().addAll(tempList);
        foodListView.setVisible(true);

    }

    public void onFavoriteFoodClick() {
        List<String> tempList = FavoriteFood.selectFavoriteFood();
        StringBuilder combinedText = new StringBuilder();

        // Clear existing items
        foodListView.getItems().clear();

        // If the list is empty, show a message
        if (tempList.isEmpty()) {
            foodListView.getItems().add("Ingen mat i databasen!");
            return;
        }

        // Add a heart emoji before each favorite food item
        List<String> formattedList = tempList.stream()
                .map(item -> "❤️ " + item) // Add the emoji before each item
                .toList();

        // Add all items to the ListView
        foodListView.getItems().addAll(formattedList);
        foodListView.setVisible(true);
    }

    public void onDislikedFoodClick() {
        List<String> tempList = DislikedFood.selectDislikedFood();
        StringBuilder combinedText = new StringBuilder();

        // Clear existing items
        foodListView.getItems().clear();

        // If the list is empty, show a message
        if (tempList.isEmpty()) {
            foodListView.getItems().add("Ingen mat i databasen!");
            return;
        }
        List<String> formattedList = tempList.stream()
                .map(item -> "\uD83D\uDCA9️ " + item) // Add the emoji before each item
                .toList();

        // Add all items to the ListView
        foodListView.getItems().addAll(formattedList);
        foodListView.setVisible(true);
    }
    public void onChangeFavoriteClick() {
        changeFavoriteFood = new TextInputDialog();
        changeFavoriteFood.setTitle("Mums");
        changeFavoriteFood.setHeaderText("Vilken mat vill du spara som mums?");

        // Show the dialog and capture the input
        Optional<String> result = changeFavoriteFood.showAndWait();
        result.ifPresent(food -> {
            if (!food.isEmpty()) {
                // Optionally, save to database
                FavoriteFood.changeToFavorite(food);
            }
        });
    }
}