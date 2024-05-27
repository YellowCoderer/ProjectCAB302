package com.example.sleepwell.controller;


import com.example.sleepwell.Statistics.IGoalsDao;
import com.example.sleepwell.initialization.MenuBar;
import com.example.sleepwell.initialization.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;

public class StatisticsController {

        @FXML
        private ListView<String> goalListView;

        @FXML
        public void addGoal(ActionEvent event) throws IOException {
            // Implement logic to add goal
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Goal");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter your goal:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(goal -> {
                goalListView.getItems().add(goal);// Replace with your logic to get the goal from input field
                IGoalsDao.saveGoalInDatabase(goal);
            });
        }


        @FXML
        public void deleteGoal() { //deletes selected goal
            int selectedIndex = goalListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedGoal = goalListView.getItems().get(selectedIndex);
                goalListView.getItems().remove(selectedIndex);
                IGoalsDao.deleteGoalFromDatabase(selectedGoal);
            }
        }
    public void onSignOut(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "login.fxml", 520, 567);
        UserSession.cleanUserSession();
        UserSession.setBrightness(0.0); // Reset back to default brightness
    }

    public void onSettings(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "settings.fxml", 600, 400);
    }

    public void onTimer(ActionEvent event) throws IOException{
        MenuBar.changeScene(event, "timer.fxml", 600, 400);
    }
    public void onHome(ActionEvent event) throws IOException {
        MenuBar.changeScene(event, "hello-view.fxml", 600, 400);
    }

}