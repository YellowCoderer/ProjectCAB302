package com.example.sleepwell.controller;

import com.example.sleepwell.initialization.MenuBar;
import com.example.sleepwell.database.Accounts;
import com.example.sleepwell.database.SqliteAccountDAO;
import com.example.sleepwell.initialization.UserSession;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * LoginController class is used for the user to connect to the main page of the application.
 */
public class LoginController {
    @FXML
    private Label loginMessageLabel;
    @FXML
    private JFXTextField usernameTextField; //done
    @FXML
    private JFXPasswordField passwordField; //done

    /**
     * This method is used to verify user credentials from a database and either login the user and redirects them to
     * the main page or displays an error message if the login fails.
     */
    public void loginButtonOnAction(ActionEvent event) throws IOException {
        SqliteAccountDAO accountDao = new SqliteAccountDAO();

        String userAccount = usernameTextField.getText();
        Accounts accountByEmail = accountDao.getAccountWithEmail(userAccount);
        Accounts accountByUsername = accountDao.getAccountWithUsername(userAccount);

        Accounts activeAccount = null;
        if (accountByEmail != null) {
            activeAccount = accountByEmail;
        } else if (accountByUsername != null) {
            activeAccount = accountByUsername;
        }

        if (activeAccount != null) {
            String password = passwordField.getText();
            if (password.equals(activeAccount.getPassword())) {
                loginMessageLabel.setText("Log in Success!");
                // Create a new user session
                UserSession.initialize(activeAccount.getUsername(), activeAccount.getId());

                //Redirect user to main page if successfully logged in
                MenuBar.changeScene(event, "hello-view.fxml", 600, 400);
            } else {
                loginMessageLabel.setText("Incorrect password.");
            }
        } else {
            loginMessageLabel.setText("No account found with the given identifier.");
        }
    }
    /**
     * The hyperlink that navigate user to the signup page
     */
    public void onSignup(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "signup.fxml", 520, 567);
    }
}

