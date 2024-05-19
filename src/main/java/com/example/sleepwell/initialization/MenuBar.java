package com.example.sleepwell.initialization;

import com.example.sleepwell.HelloApplication;
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
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            System.err.println("Error while changing scene: " + e.getMessage());
        }
    }

    public static void moveSlider(Node leftSlider, Node rightSlider, Node menu, Node menuClose, Node profile, Node profileClose) {
        try {
            // Initialize the slider's position off-screen
            leftSlider.setTranslateX(-123);
            rightSlider.setTranslateX(117);

            // Event handler for opening the menu
            menu.setOnMouseClicked(event -> {
                TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), leftSlider);
                slide.setToX(0);
                slide.play();

                slide.setOnFinished((ActionEvent e) -> {
                    menu.setVisible(false);
                    menuClose.setVisible(true);
                });
            });

            // Event handler for closing the menu
            menuClose.setOnMouseClicked(event -> {
                TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), leftSlider);
                slide.setToX(-123);
                slide.play();

                slide.setOnFinished((ActionEvent e) -> {
                    menu.setVisible(true);
                    menuClose.setVisible(false);
                });
            });


            // Event handler for opening the profile
            profile.setOnMouseClicked(event -> {
                TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), rightSlider);
                slide.setToX(0);
                slide.play();

                slide.setOnFinished((ActionEvent e) -> {
                    profile.setVisible(false);
                    profileClose.setVisible(true);
                });
            });

            // Event handler for closing the profile
            profileClose.setOnMouseClicked(event -> {
                TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), rightSlider);
                slide.setToX(117);
                slide.play();

                slide.setOnFinished((ActionEvent e) -> {
                    profile.setVisible(true);
                    profileClose.setVisible(false);
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

