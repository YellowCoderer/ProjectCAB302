package com.example.sleepwell.controller;

import com.example.sleepwell.initialization.MenuBar;
import javafx.event.ActionEvent;

/**
 * A simple and clear welcome page allows users to choose whether to go to the signup or login page.
 */
public class WelcomeController {
    /**
     * Navigate users to the signup page
     */
    public void handleSignup(ActionEvent event) throws Exception {
        MenuBar.changeScene(event, "signup.fxml", 520, 567);
    }
    /**
     * Navigate users to the login page
     */
    public void handleLogin(ActionEvent event) throws Exception {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
    }
}
