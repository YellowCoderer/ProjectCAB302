package com.example.sleepwell.controller;

import com.example.sleepwell.Statistics.Goals;
import com.example.sleepwell.Statistics.IGoalsDao;
import com.example.sleepwell.Statistics.SqliteGoalsDAO;
import com.example.sleepwell.database.SqliteAccountDAO;
import com.example.sleepwell.initialization.MenuBar;
import com.example.sleepwell.initialization.UserPreferences;
import com.example.sleepwell.initialization.UserSession;
import com.example.sleepwell.timer.SqliteTimerDAO;
import com.example.sleepwell.timer.Timer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @FXML
    private LineChart<String, Number> ComputerChart1;
    @FXML
    private LineChart<String, Number> ActivityChart2;
    @FXML
    private CategoryAxis x1, x2;
    @FXML
    private NumberAxis y1, y2;

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
        loadTimerData();
        loadActivityData();
    }

    private void loadTimerData() {
        ObservableList<Timer> timers = timerDAO.getAllTimers(scheduleUsername);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Timer timer : timers) {
            LocalDate date = LocalDate.parse(timer.getDate().get(), formatter);
            String dayOfMonth = String.valueOf(date.getDayOfMonth());
            series.getData().add(new XYChart.Data<>(dayOfMonth, Double.parseDouble(timer.getTimer().get())));
        }

        x1.setLabel("Days");

        ComputerChart1.getData().add(series);

    }
    private void loadActivityData() {
        ObservableList<Timer> timers = timerDAO.getAllTimers(scheduleUsername);

        XYChart.Series<String, Number> workoutSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> readingSeries = new XYChart.Series<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Timer timer : timers) {
            LocalDate date = LocalDate.parse(timer.getDate().get(), formatter);
            String dayOfMonth = String.valueOf(date.getDayOfMonth());
            double duration = Double.parseDouble(timer.getTimer().get());

            switch (timer.getActivity().get().toLowerCase()) {
                case "workout":
                    workoutSeries.getData().add(new XYChart.Data<>(dayOfMonth, duration));
                    break;
                case "reading":
                    readingSeries.getData().add(new XYChart.Data<>(dayOfMonth, duration));
                    break;
                default:
                    break;
            }
        }

        workoutSeries.setName("Workout");
        readingSeries.setName("Reading");

        x2.setLabel("Days");
        y2.setLabel("Hours");

        ActivityChart2.getData().addAll(workoutSeries, readingSeries);
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
