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

        Optional<String> result = addFood.showAndWait();
        result.ifPresent(food -> {
            if (!food.isEmpty()) {
                Food.insertNewtFood(food);
            }

            Dialog<ButtonType> favoriteDialog = new Dialog<>();
            favoriteDialog.setTitle("Mums");
            favoriteDialog.setHeaderText("Vill du markera detta som mums?");

            ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            favoriteDialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

            CheckBox favoriteCheckbox = new CheckBox("Ok");

            VBox content = new VBox(favoriteCheckbox);
            content.setSpacing(10);
            favoriteDialog.getDialogPane().setContent(content);


            Optional<ButtonType> favoriteResult = favoriteDialog.showAndWait();
            favoriteResult.ifPresent(button -> {
                if (button == okButtonType) {
                    if (favoriteCheckbox.isSelected()) {

                        FavoriteFood.changeToFavorite(food);
                    }
                }
            });
        });
    }

    public void onButtonOneClick() {
        List<String> tempList = Food.selectFood("SELECT * FROM mat");
        StringBuilder combinedText = new StringBuilder();

        foodListView.getItems().clear();

        if (tempList.isEmpty()) {
            foodListView.getItems().add("Ingen mat i databasen!");
            return;
        }

        foodListView.getItems().addAll(tempList);
        foodListView.setVisible(true);
    }

    public void onFavoriteFoodClick() {
        List<String> tempList = FavoriteFood.selectFavoriteFood();
        StringBuilder combinedText = new StringBuilder();

        foodListView.getItems().clear();

        if (tempList.isEmpty()) {
            foodListView.getItems().add("Ingen mat i databasen!");
            return;
        }

        List<String> formattedList = tempList.stream()
                .map(item -> "❤️ " + item)
                .toList();

        foodListView.getItems().addAll(formattedList);
        foodListView.setVisible(true);
    }

    public void onDislikedFoodClick() {
        List<String> tempList = DislikedFood.selectDislikedFood();
        StringBuilder combinedText = new StringBuilder();

        foodListView.getItems().clear();

        if (tempList.isEmpty()) {
            foodListView.getItems().add("Ingen mat i databasen!");
            return;
        }
        List<String> formattedList = tempList.stream()
                .map(item -> "\uD83D\uDCA9️ " + item) // Add the emoji before each item
                .toList();

        foodListView.getItems().addAll(formattedList);
        foodListView.setVisible(true);
    }

    public void onChangeFavoriteClick() {
        changeFavoriteFood = new TextInputDialog();
        changeFavoriteFood.setTitle("Mums");
        changeFavoriteFood.setHeaderText("Vilken mat vill du spara som mums?");

        Optional<String> result = changeFavoriteFood.showAndWait();
        result.ifPresent(food -> {
            if (!food.isEmpty()) {
                FavoriteFood.changeToFavorite(food);
            }
        });
    }
}