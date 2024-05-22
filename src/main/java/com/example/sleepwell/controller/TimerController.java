package com.example.sleepwell.controller;

import com.example.sleepwell.database.SqliteAccountDAO;
import com.example.sleepwell.initialization.MenuBar;
import com.example.sleepwell.initialization.UserPreferences;
import com.example.sleepwell.initialization.UserSession;
import com.example.sleepwell.timer.SleepSchedule;
import com.example.sleepwell.timer.SqliteSleepScheduleDAO;
import com.example.sleepwell.timer.SqliteTimerDAO;
import com.example.sleepwell.timer.Timer;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

public class TimerController {
    @FXML
    private TextField stopwatchLabel;
    @FXML
    private AnchorPane leftSlider, rightSlider, parentPane;
    @FXML
    private Label menu, menuClose;
    @FXML
    private Circle profile, profileClose;

    SqliteAccountDAO accountDao = new SqliteAccountDAO();
    UserSession session = UserSession.getInstance();
    private String userImage = accountDao.getAccount(session.getUserId()).getImage();

    public void initialize() {
        MenuBar.moveSlider(leftSlider, rightSlider, menu, menuClose, profile, profileClose);

        double chosenbrightness = UserSession.getBrightness();
        UserPreferences.adjustBrightness(parentPane, chosenbrightness);

        UserPreferences.setAvatarImage(userImage, profile);
        UserPreferences.setAvatarImage(userImage, profileClose);
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

    public void onStart() {
        startTime = System.nanoTime();
        timer.start();
    }

    public void onStop() {
        timer.stop();
        addingTimer();
    }

    public void onReset() {
        timer.stop();
        stopwatchLabel.setText("00:00:00");
    }

    //Redirect user to login page
    public void onSignOut(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
        UserSession.cleanUserSession();
        UserSession.setBrightness(0.0); // Reset back to default brightness
    }

    public void onHome(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "hello-view.fxml", 600, 400);
        SqliteSleepScheduleDAO sleepScheduleDAO = new SqliteSleepScheduleDAO();
        UserSession session = UserSession.getInstance();
        Integer id = session.getUserId();
        SleepSchedule newsleepschedule = new SleepSchedule(id, "empty","empty","empty","empty","empty","empty","empty");
        sleepScheduleDAO.addSleepSchedule(newsleepschedule);

    }

    public void onSettings(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "settings.fxml", 600, 400);
    }

    public void onStatistics(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "statistics.fxml", 600, 400);
    }

    public void editSched(ActionEvent event) throws IOException {
        SqliteSleepScheduleDAO sleepScheduleDAO = new SqliteSleepScheduleDAO();
        UserSession session = UserSession.getInstance();
        Integer id = session.getUserId();
        String monday = something.setText();
        String tuesday = something.setText();
        String wednesday = something.setText();
        String thursday = something.setText();
        String friday = something.setText();
        String saturday = something.setText();
        String sunday = something.setText();
        SleepSchedule newsleepschedule = new SleepSchedule(monday, tuesday, wednesday, thursday, friday, saturday, sunday)

        sleepScheduleDAO.updateSleepSchedule(id, newsleepschedule);

    }
    public void addingTimer() {
        SqliteTimerDAO timerDao = new SqliteTimerDAO();
        ZoneId zonedId = ZoneId.of( "Australia/Sydney" );
        LocalDate today = LocalDate.now( zonedId );
        UserSession session = UserSession.getInstance();
        int id = session.getUserId();
        String timer = stopwatchLabel.getText();
        String date = String.valueOf(today);
        String activity = "sleep";

        // Create an Timer object with the user data
        Timer newTimer = new Timer(id, timer, date, activity);

        // Add the new timer to the database
        timerDao.addTimer(newTimer);

    }


}

