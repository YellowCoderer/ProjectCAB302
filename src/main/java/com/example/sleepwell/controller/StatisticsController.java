package com.example.sleepwell.controller;

import com.example.sleepwell.database.Goals;
import com.example.sleepwell.database.IGoalsDAO;
import com.example.sleepwell.database.SqliteGoalsDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class StatisticsController extends HelloController {
    @FXML
    private ListView<Goals> goalListView;
    @FXML
    private TextField goalTextField;
    private IGoalsDAO GoalsDao;



    public StatisticsController() {GoalsDao = new SqliteGoalsDAO();

    }

    private void selectGoals(Goals goals) {
        goalListView.getSelectionModel().select(goals);
        goalTextField.setText(goals.getNewGoal());
    }
    private ListCell<Goals> renderCell(ListView<Goals> goalListView) {
        return new ListCell<>() {

            private void onContactSelected(MouseEvent mouseEvent) {
                ListCell<Goals> clickedCell = (ListCell<Goals>) mouseEvent.getSource();
                // Get the selected contact from the list view
                Goals selectedContact = clickedCell.getItem();
                if (selectedContact != null) selectGoals(selectedContact);
            }


            @Override
            protected void updateItem(Goals goals, boolean empty) {
                super.updateItem(goals, empty);
                // If the cell is empty, set the text to null, otherwise set it to the goal
                if (empty || goals == null || goals.getNewGoal() == null) {
                    setText(null);
                    super.setOnMouseClicked(this::onContactSelected);
                } else {
                    setText(goals.getNewGoal());
                }
            }
        };
    }
    private void syncContacts() {
        goalListView.getItems().clear();
        List<Goals> goals = GoalsDao.getAllGoals();
        boolean hasContact = !goals.isEmpty();
        if (hasContact) {
            goalListView.getItems().addAll(goals);
        }


    }

    @FXML
    public void initialize () {
        goalListView.setCellFactory(this::renderCell);
        syncContacts();
        //select goal and display it
        goalListView.getSelectionModel().selectFirst();
        Goals firstGoal = goalListView.getSelectionModel().getSelectedItem();
        if (firstGoal != null) {
            selectGoals(firstGoal);
        }
    }



    @FXML
    public void onDelete() {
        Goals selectedGoal = goalListView.getSelectionModel().getSelectedItem();
        if (selectedGoal != null) {
            GoalsDao.deleteGoal(selectedGoal);
            syncContacts();
        }
    }

    @FXML
    public void onAdd() {

        final String DEFAULT_Goal = "New";
        Goals newGoal = new Goals(DEFAULT_Goal);
        // Add to database
        GoalsDao.addGoals(newGoal);
        syncContacts();

        selectGoals(newGoal);
        goalTextField.requestFocus();
    }



}

