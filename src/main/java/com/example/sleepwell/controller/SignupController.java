package com.example.sleepwell.controller;
import com.example.sleepwell.MenuBar;

import com.example.sleepwell.database.Accounts;
import com.example.sleepwell.database.SqliteAccountDAO;
import com.example.sleepwell.database.UserSession;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController {
    SqliteAccountDAO accountDao = new SqliteAccountDAO();

    //implements database
    public SignupController() {
    }

    @FXML
    private Label signupMessageLabel;
    @FXML
    private JFXPasswordField passwordField, confirmPasswordField;
    @FXML
    private JFXTextField usernameTextField, firstnameTextField, lastnameTextField, emailTextField, phoneTextField;

    @FXML
    public void signupButtonOnAction(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String password = passwordField.getText();

        // Check for empty fields first
        if (username.isEmpty() || email.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty()) {
            signupMessageLabel.setTextFill(Color.RED);
            signupMessageLabel.setText("There is an empty field!");
            return; // Stop further execution
        } else {
            // Check if the passwords match
            if (!passwordField.getText().equals(confirmPasswordField.getText())) {
                signupMessageLabel.setTextFill(Color.RED);
                signupMessageLabel.setText("Passwords do not match!");
                return; // Stop further execution
            }
        }

        // If all checks pass, register the user
        registerUser();

        Accounts userAccount = accountDao.getAccountWithEmail(email);
        UserSession.getInstance(userAccount.getUsername(), userAccount.getId());

        //Redirect user to main page if successfully registered
        MenuBar.changeScene(event, "hello-view.fxml", 600, 400);
    }

    public void registerUser() {
        String username = usernameTextField.getText();
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String password = passwordField.getText();

        // Create an Accounts object with the user data
        Accounts newAccount = new Accounts(username, firstname, lastname, email, phone, password);

        // Add the new account to the database
        accountDao.addAccount(newAccount);

        signupMessageLabel.setTextFill(Color.BLACK);
        signupMessageLabel.setText("User has been signed up successfully");
    }

    public void onLogin(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
    }
}
