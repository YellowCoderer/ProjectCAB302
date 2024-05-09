package com.example.sleepwell.controller;
import com.example.sleepwell.controller.HelloController;

import com.example.sleepwell.HelloApplication;
import com.example.sleepwell.database.Accounts;
import com.example.sleepwell.database.SqliteAccountDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SignupController implements Initializable {

    //implements database
    public SignupController() {
        SqliteAccountDAO accountDao = new SqliteAccountDAO();
    }

    @FXML
    private Label signupMessageLabel, confirmPasswordLabel;
    @FXML
    private Button closeButton;
    @FXML
    private PasswordField setPasswordField, confirmPasswordField;
    @FXML
    private ImageView sleepImageView;
    @FXML
    private TextField firstnameTextField, lastnameTextField, emailTextField;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File sleepFile = new File("src/main/resources/images/enough-sleep.png");
        Image sleepImage = new Image(sleepFile.toURI().toString());
        sleepImageView.setImage(sleepImage);
    }

    @FXML
    public void signupButtonOnAction(ActionEvent event) throws IOException {
        if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
            confirmPasswordLabel.setText("");

            //Redirect user to main page if successfully registered
            Stage stage = (Stage) signupMessageLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Home
            stage.setScene(scene);
        }
        else {
            confirmPasswordLabel.setText("Password does not match");
        }
    }

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser() {
        SqliteAccountDAO accountDao = new SqliteAccountDAO();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String email = emailTextField.getText();
        String password = setPasswordField.getText();

        // Check if email contains '@' sign
        if (!email.contains("@")) {
            signupMessageLabel.setText("Invalid email address");
            return; // Stop further execution
        }

        // Create an Accounts object with the user data
        Accounts newAccount = new Accounts(firstname, lastname, email, password);

        // Add the new account to the database
        accountDao.addAccount(newAccount);

        signupMessageLabel.setText("User has been signed up successfully");
    }
}
