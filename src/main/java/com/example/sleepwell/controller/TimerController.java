package com.example.sleepwell.controller;

import com.example.sleepwell.HelloApplication;
import com.example.sleepwell.database.SqliteAccountDAO;
import com.example.sleepwell.database.SqliteTimerDAO;
import com.jfoenix.controls.JFXButton;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TimerController extends HelloController {
    public TimerController() {
        SqliteTimerDAO timerDao = new SqliteTimerDAO();
    }
    @FXML
    private Label stopwatchLabel;
    @FXML
    private JFXButton homeButton;
    //Home linked with Timer Page
    public void openHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Home
        stage.setScene(scene);
    }

    private long startTime;

    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            long elapsedMillis = (System.nanoTime() - startTime) / 1_000_000;
            long seconds = (elapsedMillis / 1000) % 60;
            long minutes = (elapsedMillis / (1000 * 60)) % 60;
            long hours = (elapsedMillis / (1000 * 60 * 60)) % 24;
            stopwatchLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        }
    };

    public void startStopwatch() {
        startTime = System.nanoTime();
        timer.start();
    }

    public void stopStopwatch() {
        timer.stop();
    }

    public void resetStopwatch() {
        timer.stop();
        stopwatchLabel.setText("00:00:00");
    }
}

