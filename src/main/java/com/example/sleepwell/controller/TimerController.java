package com.example.sleepwell.controller;

import com.example.sleepwell.HelloApplication;
import com.example.sleepwell.database.Timer;
import com.example.sleepwell.database.UserSession;
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
import java.time.LocalDate;
import java.time.ZoneId;

import static java.lang.Long.parseLong;


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
    private long getTimer;

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
        addingTimer();
    }

    public void resetStopwatch() {
        timer.stop();
        stopwatchLabel.setText("00:00:00");
    }

    public void addingTimer() {
        SqliteTimerDAO timerDao = new SqliteTimerDAO();
        ZoneId zonedId = ZoneId.of( "Australia/Sydney" );
        LocalDate today = LocalDate.now( zonedId );
        int id = 1;
        String timer = stopwatchLabel.getText();
        String date = String.valueOf(today);
        String activity = "sleep";

        // Create an Timer object with the user data
        Timer newTimer = new Timer(id, timer, date, activity);

        // Add the new timer to the database
        timerDao.addTimer(newTimer);

    }
}

