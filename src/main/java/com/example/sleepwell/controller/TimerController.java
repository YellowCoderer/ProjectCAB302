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

    public void initialize() {
        MenuBar.moveSlider(leftSlider, rightSlider, menu, menuClose, profile, profileClose);

        double chosenBrightness = UserSession.brightnessAdjustAutoVal();
        UserPreferences.adjustBrightness(parentPane, chosenBrightness);

        UserPreferences.setAvatarImage(userImage, profile);
        UserPreferences.setAvatarImage(userImage, profileClose);

        ObservableList<Timer> timerTimer = timerDAO.getAllTimers(scheduleUsername);

        timeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Timer, String>("timer"));
        dateColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Timer, String>("date"));
        activityColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Timer, String>("activity"));

        TreeItem<Timer> root = new RecursiveTreeItem<>(timerTimer, RecursiveTreeObject::getChildren);
        actHistory.setRoot(root);
        actHistory.setShowRoot(false);


        setupActivityBox();//initialise activity box
        setSchedule(); //visualise schedule

        new SqliteSleepScheduleDAO();
    }

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

    public void onSettings(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "settings.fxml", 600, 400);
    }

    public void onStatistics(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "statistics.fxml", 600, 400);
    }

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

    public void addingTimer() {
        ZoneId zonedId = ZoneId.of( "Australia/Sydney" );
        LocalDate today = LocalDate.now( zonedId );
        String timer = stopwatchLabel.getText();
        String date = String.valueOf(today);
        String activity = pickList.getValue();

        // Create a Timer object with the user data
        Timer newTimer = new Timer(scheduleUsername, timer, date, activity);

        // Add the new timer to the database
        timerDAO.addTimer(newTimer);
    }
}

