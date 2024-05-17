package com.example.sleepwell.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomeController {

    public void handleSignUp(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sleepwell/signup.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 520, 567));  // Dimensions set as per your original Login and Signup forms
        stage.show();
    }

    public void handleLogIn(ActionEvent event) throws Exception {
        // Make sure the path is correct based on your project structure
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/sleepwell/login.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 520, 567));  // Same dimensions
        stage.show();
    }
}
