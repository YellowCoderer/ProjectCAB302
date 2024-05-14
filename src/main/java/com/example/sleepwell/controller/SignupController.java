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
import javafx.scene.paint.Color;

import java.io.IOException;

public class SignupController {
    SqliteAccountDAO accountDao = new SqliteAccountDAO();

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
            // Check if email contains @ symbol
            if (!email.contains("@")) {
                signupMessageLabel.setTextFill(Color.RED);
                signupMessageLabel.setText("Invalid email address!");
                return; // Stop further execution
            }

            // Check if username already exists
            if (accountDao.getAccountWithUsername(username) != null) {
                signupMessageLabel.setTextFill(Color.RED);
                signupMessageLabel.setText("Username already exists!");
                return; // Stop further execution
            }

            // Check if email already exists
            if (accountDao.getAccountWithEmail(email) != null) {
                signupMessageLabel.setTextFill(Color.RED);
                signupMessageLabel.setText("Email already exists!");
                return; // Stop further execution
            }
        }

        registerUser(); // If all checks pass, register the user

        Accounts userAccount = accountDao.getAccountWithEmail(email);
        UserSession.initialize(userAccount.getUsername(), userAccount.getId());

        //Redirect user to main page if successfully registered
        MenuBar.changeScene(event, "login.fxml", 520, 567);
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
