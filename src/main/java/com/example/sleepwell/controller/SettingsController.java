package com.example.sleepwell.controller;

import com.example.sleepwell.MenuBar;
import com.example.sleepwell.database.Accounts;
import com.example.sleepwell.database.SqliteAccountDAO;
import com.example.sleepwell.database.UserSession;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class SettingsController {
    @FXML
    private JFXTextField firstNameTextField, lastNameTextField, emailTextField, usernameTextField, phoneTextField;

    @FXML
    private Text errorMessage;

    @FXML
    private AnchorPane LeftSlider, RightSlider;

    @FXML
    private Label Menu, MenuClose;

    @FXML
    private ImageView Profile, ProfileClose;

    SqliteAccountDAO accountDao = new SqliteAccountDAO();
    UserSession session = UserSession.getInstance();

    public void initialize() {
        firstNameTextField.setText(accountDao.getAccount(session.userId()).getFirstName());
        lastNameTextField.setText(accountDao.getAccount(session.userId()).getLastName());
        emailTextField.setText(accountDao.getAccount(session.userId()).getEmail());
        usernameTextField.setText(accountDao.getAccount(session.userId()).getUsername());
        phoneTextField.setText(accountDao.getAccount(session.userId()).getPhone());

        MenuBar.moveSlider(LeftSlider, RightSlider, Menu, MenuClose, Profile, ProfileClose);
    }

    @FXML
    private void onUpdateProfile(ActionEvent event) {
        String username = usernameTextField.getText();
        String firstname = firstNameTextField.getText();
        String lastname = lastNameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String password = accountDao.getAccount(session.userId()).getPassword();
        Integer id = accountDao.getAccount(session.userId()).getId();

        Accounts newAccount = new Accounts(username, firstname, lastname, email, phone, password);

        // Validation logic
        if (username.isEmpty() || email.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty()) {
            // Display error message to the user
            errorMessage.setFill(Color.RED);
            errorMessage.setText("*There is a missing field!*");
        } else {
            // Add the new account to the database
            errorMessage.setFill(Color.BLACK);
            errorMessage.setText("Success!");
            accountDao.updateAccount(id, newAccount);
        }
    }

    //Redirect user to login page
    public void onSignOut(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
        UserSession.cleanUserSession();
    }

    public void onSettings(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "settings.fxml", 600, 400);
    }
}
