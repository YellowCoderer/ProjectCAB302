package com.example.sleepwell;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        String stylesheet = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
        stage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}