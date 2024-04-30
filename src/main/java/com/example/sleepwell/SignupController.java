package com.example.sleepwell;

import com.example.sleepwell.database.Accounts;
import com.example.sleepwell.database.SqliteAccountDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class SignupController implements Initializable {

    //implements database
    public SignupController() {
        SqliteAccountDAO accountDao = new SqliteAccountDAO();
    }

    @FXML
    private Label signupMessageLabel;
    @FXML
    private Button closeButton;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private ImageView sleepImageView;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField emailTextField;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File sleepFile = new File("src/main/resources/images/enough-sleep.png");
        Image sleepImage = new Image(sleepFile.toURI().toString());
        sleepImageView.setImage(sleepImage);
    }
    public void signupButtonOnAction(ActionEvent event){

        if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
            confirmPasswordLabel.setText("");

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

        // Create an Accounts object with the user data
        Accounts newAccount = new Accounts(firstname, lastname, email, password);

        // Add the new account to the database
        accountDao.addAccount(newAccount);

        signupMessageLabel.setText("User has been signed up successfully");
    }

}
