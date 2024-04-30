package com.example.sleepwell.controller;


import com.example.sleepwell.database.Accounts;
import com.example.sleepwell.database.SqliteAccountDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class LoginController {
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField emailTextField; //done
    @FXML
    private PasswordField passwordTextField; //done
    @FXML
    private Label forgotPasswordLabel;
    @FXML
    private Button  loginButton; //done
    @FXML
    private Label singUpLabel;

    public void loginButtonOnAction(ActionEvent event) {
        SqliteAccountDAO accountDao = new SqliteAccountDAO();

        String email = emailTextField.getText();
        Accounts userAccount = accountDao.getAccountWithEmail(email);

        if (userAccount != null) {
            String password = passwordTextField.getText();
            if (password.equals(userAccount.getPassword())) {
                loginMessageLabel.setText("Log in Success!");
            } else {
                loginMessageLabel.setText("Incorrect password.");
            }
        } else {
            loginMessageLabel.setText("No account found with the given email.");
        }
    }



}
