package com.example.sleepwell;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class MenuBar {
    public static void changeScene(ActionEvent event, String fxmlPath, int width, int height) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        stage.setScene(scene);
    }

    public static void moveSlider(Node leftSlider, Node rightSlider, Node menu, Node menuClose, Node profile, Node profileClose) {
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
    }

    // Adjust brightness method that can be called independently
    public static void adjustBrightness(AnchorPane parentPane, double brightness) {
        ColorAdjust globalColorAdjust = new ColorAdjust();
        globalColorAdjust.setBrightness(brightness);
        Platform.runLater(() -> { // Delay the execution so that program has time to change scene brightness
            Scene scene = parentPane.getScene();
            if (scene != null) {
                Parent root = (Parent) scene.getRoot();
                root.setEffect(globalColorAdjust);
            }
        });
    }

    public static void setAvatarImage(String imageName, Circle accountImage) {
        File file = new File("src/main/resources/com/example/sleepwell/images/", imageName);
        Image image = new Image(file.toURI().toString());
        accountImage.setFill(new ImagePattern(image));
    }
}
