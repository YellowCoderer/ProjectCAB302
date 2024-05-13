package com.example.sleepwell.controller;

import com.example.sleepwell.HelloApplication;
import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label Menu, MenuClose;

    @FXML
    private ImageView Profile, ProfileClose;

    @FXML
    private JFXButton Signout;

    @FXML
    private JFXButton timerButton;

    @FXML
    private AnchorPane LeftSlider, RightSlider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the slider's position off-screen
        LeftSlider.setTranslateX(-123);
        RightSlider.setTranslateX(117);

        // Event handler for opening the menu
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), LeftSlider);
            slide.setToX(0);
            slide.play();

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(false);
                MenuClose.setVisible(true);
            });
        });

        // Event handler for closing the menu
        MenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), LeftSlider);
            slide.setToX(-123);
            slide.play();

            slide.setOnFinished((ActionEvent e) -> {
                Menu.setVisible(true);
                MenuClose.setVisible(false);
            });
        });


        // Event handler for opening the profile
        Profile.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), RightSlider);
            slide.setToX(0);
            slide.play();

            slide.setOnFinished((ActionEvent e) -> {
                Profile.setVisible(false);
                ProfileClose.setVisible(true);
            });
        });

        // Event handler for closing the profile
        ProfileClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), RightSlider);
            slide.setToX(117);
            slide.play();

            slide.setOnFinished((ActionEvent e) -> {
                Profile.setVisible(true);
                ProfileClose.setVisible(false);
            });
        });
    }

    //Redirect user to login page
    public void onSignOut(ActionEvent event) throws IOException {
        Stage stage = (Stage) Signout.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 567);
        stage.setScene(scene);
    }

    //Open Timer Page
    public void openTimer(ActionEvent event) throws IOException {
        Stage stage = (Stage) timerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("timer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Home
        stage.setScene(scene);
    }
}






