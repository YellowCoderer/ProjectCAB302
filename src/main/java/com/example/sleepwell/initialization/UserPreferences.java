package com.example.sleepwell.initialization;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;

public class UserPreferences {
    // Code below taken from: Detrua. (2022). JavaFX - Change brightness of scene [Answer to forum post]. Stack Overflow. https://stackoverflow.com/questions/71979647/javafx-change-brightness-of-scene
    // Adjust brightness method that can be called independently
    public static void adjustBrightness(AnchorPane parentPane, double brightness) {
        try {
            ColorAdjust globalColorAdjust = new ColorAdjust();
            globalColorAdjust.setBrightness(brightness); // Set brightness of scene
            Platform.runLater(() -> { // Delay the execution so that program has time to change scene brightness
                try {
                    Scene scene = parentPane.getScene();
                    if (scene != null) {
                        Parent root = scene.getRoot();
                        if (root != null) {
                            root.setEffect(globalColorAdjust); // Change brightness of scene
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error adjusting brightness: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            System.err.println("Failed to schedule brightness adjustment: " + e.getMessage());
        }
    }

    // Adjust brightness method that can be called independently
    public static void setAvatarImage(String imageName, Circle accountImage) {
        try {
            File file = new File("src/main/resources/com/example/sleepwell/images/", imageName);
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                accountImage.setFill(new ImagePattern(image));
            } else {
                System.err.println("Image file does not exist: " + imageName);
            }
        } catch (Exception e) {
            System.err.println("Error setting avatar image: " + e.getMessage());
        }
    }
}