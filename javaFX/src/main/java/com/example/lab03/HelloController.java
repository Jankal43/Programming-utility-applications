package com.example.lab03;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHello() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}