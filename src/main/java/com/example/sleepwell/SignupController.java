package com.example.sleepwell;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private Hyperlink loginLink;
    @FXML
    private Label signupMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;

    public void signupButtonOnAction(ActionEvent event){
        signupMessageLabel.setText("User has been signup successfully");
    }
//    public void loginLink(ActionEvent event) throws Exception {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
//        Parent root = loader.load();
//
//        Scene scene = new Scene(root);
//
//        Stage stage = (Stage) loginLink.getScene().getWindow();
//
//        stage.setScene(scene);
//        stage.show;
//    }

    public void registerUser(){

        if (setPasswordField.getText().equals(confirmPasswordField.getText())) {

        }
        else {

        }
    }
}
