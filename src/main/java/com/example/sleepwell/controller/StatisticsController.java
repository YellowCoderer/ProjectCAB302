package com.example.sleepwell.controller;

import com.example.sleepwell.Statistics.Goals;
import com.example.sleepwell.Statistics.IGoalsDao;
import com.example.sleepwell.Statistics.SqliteGoalsDAO;
import com.example.sleepwell.database.SqliteAccountDAO;
import com.example.sleepwell.initialization.MenuBar;
import com.example.sleepwell.initialization.UserPreferences;
import com.example.sleepwell.initialization.UserSession;
import com.example.sleepwell.timer.SqliteTimerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class StatisticsController {
    @FXML
    private Circle profile, profileClose;
    @FXML
    private ListView<String> goalListView;
    @FXML
    private AnchorPane leftSlider, rightSlider, parentPane;
    @FXML
    private Label menu, menuClose;

    private SqliteAccountDAO accountDao = new SqliteAccountDAO();
    private SqliteTimerDAO timerDAO = new SqliteTimerDAO();
    private IGoalsDao goalsDao = new SqliteGoalsDAO();
    private UserSession session = UserSession.getInstance();
    private String scheduleUsername = session.getUsername();
    private String userImage = accountDao.getAccount(session.getUserId()).getImage();

    public void initialize() {
        MenuBar.moveSlider(leftSlider, rightSlider, menu, menuClose, profile, profileClose);

        double chosenBrightness = UserSession.brightnessAdjustAutoVal();
        UserPreferences.adjustBrightness(parentPane, chosenBrightness);

        UserPreferences.setAvatarImage(userImage, profile);
        UserPreferences.setAvatarImage(userImage, profileClose);

        loadGoals();    // add the users goals to the list view
    }

    private void loadGoals() {
        List<String> goals = goalsDao.getGoalsForUser(scheduleUsername);
        goalListView.getItems().addAll(goals);
    }

    @FXML                                       // add a goal to the list view
    public void addGoal(ActionEvent event) throws IOException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Goal");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter your goal:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(goalText -> {
            goalListView.getItems().add(goalText);  // add that goal to the database
            Goals goal = new Goals(scheduleUsername, goalText);
            goalsDao.saveGoalInDatabase(goal);
        });
    }

    @FXML                       // delete selected goal from database
    public void deleteGoal() {
        int selectedIndex = goalListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedGoal = goalListView.getItems().get(selectedIndex);
            goalListView.getItems().remove(selectedIndex);
            goalsDao.deleteGoalFromDatabase(selectedGoal);
        }
    }

    public void onSignOut(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
        UserSession.cleanUserSession();
        UserSession.setBrightness(0.0);
    }

    public void onSettings(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "settings.fxml", 600, 400);
    }

    public void onTimer(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "timer.fxml", 600, 400);
    }

    public void onHome(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "hello-view.fxml", 600, 400);
    }
}
