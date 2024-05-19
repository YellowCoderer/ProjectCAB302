package com.example.sleepwell.controller;

import com.example.sleepwell.initialization.MenuBar;
import javafx.event.ActionEvent;

public class WelcomeController {

    public void handleSignup(ActionEvent event) throws Exception {
        MenuBar.changeScene(event, "signup.fxml", 520, 567);
    }

    public void handleLogin(ActionEvent event) throws Exception {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
    }
}
