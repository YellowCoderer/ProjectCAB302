package com.example.sleepwell;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MenuBar {
    public static void changeScene(ActionEvent event, String fxmlPath, int width, int height) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setScene(scene);
    }

    public static void moveSlider(Node LeftSlider, Node RightSlider, Node Menu, Node MenuClose, Node Profile, Node ProfileClose) {
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
}
