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
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

public class TimerController {
    @FXML
    private TextField stopwatchLabel;
    @FXML
    private AnchorPane leftSlider, rightSlider, parentPane;
    @FXML
    private Label menu, menuClose;
    @FXML
    private Circle profile, profileClose;
    @FXML
    private TextField monField;
    @FXML
    private TextField tueField;
    @FXML
    private TextField wedField;
    @FXML
    private TextField thuField;
    @FXML
    private TextField friField;
    @FXML
    private TextField satField;
    @FXML
    private TextField sunField;
    @FXML
    private ComboBox<String> pickList;

    SqliteAccountDAO accountDao = new SqliteAccountDAO();
    UserSession session = UserSession.getInstance();
    private String userImage = accountDao.getAccount(session.getUserId()).getImage();

    public void initialize() {
        MenuBar.moveSlider(leftSlider, rightSlider, menu, menuClose, profile, profileClose);

        double chosenbrightness = UserSession.brightnessAdjustAutoVal();
        UserPreferences.adjustBrightness(parentPane, chosenbrightness);

        UserPreferences.setAvatarImage(userImage, profile);
        UserPreferences.setAvatarImage(userImage, profileClose);

        setupActivityBox();//initialise activity box

    }
    private void setupActivityBox() {
        List<String> options = Arrays.asList("sleep", "workout", "reading");
        pickList.getItems().addAll(options);
        pickList.getSelectionModel().selectFirst();
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
    }

    public void startup(){
        SqliteSleepScheduleDAO sleepScheduleDAO = new SqliteSleepScheduleDAO();
        UserSession session = UserSession.getInstance();
        Integer id = session.getUserId();
        SleepSchedule newsleepschedule = new SleepSchedule(id, "empty","empty","empty","empty","empty","empty","empty");
        sleepScheduleDAO.addSleepSchedule(newsleepschedule);
    }

    public void addingHistory() {
        SqliteTimerDAO timerDao = new SqliteTimerDAO();
        UserSession session = UserSession.getInstance();
        Integer id = session.getUserId();
        timerDao.getAllTimer(id);
    }

    public void onSettings(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "settings.fxml", 600, 400);
    }

    public void onStatistics(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "statistics.fxml", 600, 400);
    }

    public void editSched(ActionEvent event) throws IOException {
        startup();
        SqliteSleepScheduleDAO sleepScheduleDAO = new SqliteSleepScheduleDAO();
        UserSession session = UserSession.getInstance();
        Integer id = session.getUserId();


        String monday = monField.getText();
        String tuesday = tueField.getText();
        String wednesday = wedField.getText();
        String thursday = thuField.getText();
        String friday = friField.getText();
        String saturday = satField.getText();
        String sunday = sunField.getText();


        SleepSchedule newSleepSchedule = new SleepSchedule(id, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        sleepScheduleDAO.updateSleepSchedule(newSleepSchedule);
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

        // Create a Timer object with the user data
        Timer newTimer = new Timer(id, timer, date, activity);

        // Add the new timer to the database
        timerDao.addTimer(newTimer);

    }


}

