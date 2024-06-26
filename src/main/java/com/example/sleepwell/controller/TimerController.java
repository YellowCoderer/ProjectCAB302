package com.example.sleepwell.controller;

import com.example.sleepwell.database.SqliteAccountDAO;
import com.example.sleepwell.initialization.MenuBar;
import com.example.sleepwell.initialization.UserPreferences;
import com.example.sleepwell.initialization.UserSession;
import com.example.sleepwell.timer.*;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

/** All the functions for the timer page */
public class TimerController {
    @FXML
    private AnchorPane leftSlider, rightSlider, parentPane;
    @FXML
    private Label menu, menuClose, stopwatchLabel;
    @FXML
    private Circle profile, profileClose;
    @FXML
    private TextField monField, tueField, wedField, thuField, friField, satField, sunField;
    @FXML
    private ComboBox<String> pickList;
    @FXML
    private JFXTreeTableView actHistory;
    @FXML
    private JFXTreeTableColumn timeColumn, activityColumn, dateColumn;

    SqliteAccountDAO accountDao = new SqliteAccountDAO();
    SqliteTimerDAO timerDAO = new SqliteTimerDAO();
    UserSession session = UserSession.getInstance();
    String scheduleUsername = session.getUsername();
    private String userImage = accountDao.getAccount(session.getUserId()).getImage();

    SqliteSleepScheduleDAO schedDAO = new SqliteSleepScheduleDAO();

    /**initialise user's time history and sleepschedule*/
    public void initialize() {
        MenuBar.moveSlider(leftSlider, rightSlider, menu, menuClose, profile, profileClose);

        double chosenBrightness = UserSession.brightnessAdjustAutoVal();
        UserPreferences.adjustBrightness(parentPane, chosenBrightness);

        UserPreferences.setAvatarImage(userImage, profile);
        UserPreferences.setAvatarImage(userImage, profileClose);

        // Shows the user timer history
        ObservableList<Timer> timerTimer = timerDAO.getAllTimers(scheduleUsername);

        timeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Timer, String>("timer"));
        dateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Timer, String>("date"));
        activityColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Timer, String>("activity"));

        TreeItem<Timer> root = new RecursiveTreeItem<>(timerTimer, RecursiveTreeObject::getChildren);
        actHistory.setRoot(root);
        actHistory.setShowRoot(false);

        // Sets up the user sleepschedule
        setupActivityBox();//initialise activity box
        setSchedule(); //visualise schedule

        new SqliteSleepScheduleDAO();
    }

    /** set's user's sleepschedule*/
    public void setSchedule() {
        SleepSchedule schedule = schedDAO.getSleepSchedule(session.getUsername());

        // Check if the schedule object is not null
        if (schedule != null) {
            tueField.setText(schedule.getTuesday());
            wedField.setText(schedule.getWednesday());
            thuField.setText(schedule.getThursday());
            friField.setText(schedule.getFriday());
            satField.setText(schedule.getSaturday());
            sunField.setText(schedule.getSunday());
            monField.setText(schedule.getMonday());
            //Returns 21:00 for user's sleepschedule as default
        } else {
            // Handle the case where schedule is null, maybe log an error or set default values
            SleepSchedule newSleepSchedule = new SleepSchedule(scheduleUsername, "21:00", "21:00", "21:00", "21:00", "21:00", "21:00", "21:00");
            schedDAO.addSleepSchedule(newSleepSchedule);

            monField.setText("21:00");
            tueField.setText("21:00");
            wedField.setText("21:00");
            thuField.setText("21:00");
            friField.setText("21:00");
            satField.setText("21:00");
            sunField.setText("21:00");
        }
    }

    /** A list of activities user's can choose for the timer */
    private void setupActivityBox() {
        List<String> options = Arrays.asList("sleep", "workout", "reading");
        pickList.getItems().addAll(options);
        pickList.getSelectionModel().selectFirst();
    }
    private long startTime;
    /** Displays the timer for the user */
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
    /** Function of the start button on timer */
    public void onStart() {
        startTime = System.nanoTime();
        timer.start();
    }
    /** Function of the stop button on timer and saves user's time */
    public void onStop() {
        timer.stop();
        addingTimer();
    }
    /** Resets the user's timer */
    public void onReset() {
        timer.stop();
        stopwatchLabel.setText("00:00:00");
    }

    /**Redirect user to login page*/
    public void onSignOut(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
        UserSession.cleanUserSession();
        UserSession.setBrightness(0.0); // Reset back to default brightness
    }
    /** Takes user to home */
    public void onHome(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "hello-view.fxml", 600, 400);
    }
    /** Takes user to setting */
    public void onSettings(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "settings.fxml", 600, 400);
    }
    /** Takes user to statistics */
    public void onStatistics(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "statistics.fxml", 600, 400);
    }
    /** Allows user to edit their sleep schedule */
    public void editSched(ActionEvent event) throws IOException {
        String monday = monField.getText();
        String tuesday = tueField.getText();
        String wednesday = wedField.getText();
        String thursday = thuField.getText();
        String friday = friField.getText();
        String saturday = satField.getText();
        String sunday = sunField.getText();

        SleepSchedule newSleepSchedule = new SleepSchedule(scheduleUsername, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
        schedDAO.updateSleepSchedule(scheduleUsername, newSleepSchedule);
    }
    /** Grabs user's timer time and activity */
    public void addingTimer() {
        ZoneId zonedId = ZoneId.of("Australia/Sydney");
        LocalDate today = LocalDate.now(zonedId);
        String timerString = stopwatchLabel.getText();

        // Splitting the timerString using ":" delimiter
        String[] timeComponents = timerString.split(":");

        // Extracting hours, minutes, and seconds
        int hours = Integer.parseInt(timeComponents[0]);
        int minutes = Integer.parseInt(timeComponents[1]);
        int seconds = Integer.parseInt(timeComponents[2]);

        // Calculating the total time in hours
        double totalHours = hours + (minutes / 60.0) + (seconds / 3600.0);
        double roundedHours = Math.ceil(totalHours * 2) / 2.0;


        String date = String.valueOf(today);
        String activity = pickList.getValue();

        // Create a Timer object with the user data
        Timer newTimer = new Timer(scheduleUsername, String.format("%.1f", roundedHours), date, activity);

        // Add the new timer to the database
        timerDAO.addTimer(newTimer);
    }

}

