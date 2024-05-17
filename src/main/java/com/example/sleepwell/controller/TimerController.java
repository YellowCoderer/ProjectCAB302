package com.example.sleepwell.controller;

import com.example.sleepwell.database.SqliteTimerDAO;
import com.example.sleepwell.database.Timer;
import com.example.sleepwell.database.UserSession;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.ZoneId;

public class TimerController extends HelloController {
    @FXML
    private Label stopwatchLabel;

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

    public void addingTimer() {
        SqliteTimerDAO timerDao = new SqliteTimerDAO();
        ZoneId zonedId = ZoneId.of( "Australia/Sydney" );
        LocalDate today = LocalDate.now( zonedId );
        UserSession session = UserSession.getInstance();
        int id = session.userId();
        String timer = stopwatchLabel.getText();
        String date = String.valueOf(today);
        String activity = "sleep";

        // Create an Timer object with the user data
        Timer newTimer = new Timer(id, timer, date, activity);

        // Add the new timer to the database
        timerDao.addTimer(newTimer);

    }

}

