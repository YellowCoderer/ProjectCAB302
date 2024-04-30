package com.example.sleepwell;

import com.example.sleepwell.database.Accounts;
import com.example.sleepwell.database.SqliteAccountDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;


public class SignupController implements Initializable {

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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        File sleepFile = new File("src/main/resources/images/enough-sleep.png");
        Image sleepImage = new Image(sleepFile.toURI().toString());
        sleepImageView.setImage(sleepImage);
    }
    public void signupButtonOnAction(ActionEvent event){

        if (setPasswordField.getText().equals(confirmPasswordField.getText())) {
//            registerUser();
            confirmPasswordLabel.setText("");
            signupMessageLabel.setText("User has been signup successfully");
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

//    public void registerUser(){
//        DatabaseConnection connectNow = new DatabaseConnection();
//        Connection connectDB = connectNow.getConnection();
//
//        String firstname = "";
//        String lastname = "";
//        String username = "";
//
//    }
}
