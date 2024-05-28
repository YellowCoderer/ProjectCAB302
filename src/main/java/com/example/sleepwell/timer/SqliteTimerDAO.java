package com.example.sleepwell.timer;
import com.example.sleepwell.database.SqliteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SqliteTimerDAO implements ITimerDAO {
    private Connection connection = SqliteConnection.getInstance();

    public SqliteTimerDAO() {
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS timers ("
                    + "userid INTEGER NOT NULL,"
                    + "timer FLOAT NOT NULL,"
                    + "date DATE NOT NULL,"
                    + "activity STRING NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTimer(Timer timer) {
        String insertQuery = "INSERT INTO timers (userid, timer, date, activity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            // Use the get() method of StringProperty to extract the string value
            statement.setString(1, timer.getTimerId());
            statement.setString(2, timer.getTimer().get());  // Get actual string value
            statement.setString(3, timer.getDate().get());   // Get actual string value
            statement.setString(4, timer.getActivity().get()); // Get actual string value

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Timer getTimer(String userName) {

        String query = "SELECT * FROM timers WHERE userid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("userid");
                String timer = resultSet.getString("timer");
                String date = resultSet.getString("date");
                String activity = resultSet.getString("activity");

                Timer timer1 = new Timer(userName, timer, date, activity);
                return (Timer) timer1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ObservableList<Timer> getAllTimers(String userName) {
        String query = "SELECT * FROM timers WHERE userid = ?";
        ObservableList<Timer> timers = FXCollections.observableArrayList();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String timer = resultSet.getString("timer");
                String date = resultSet.getString("date");
                String activity = resultSet.getString("activity");

                Timer timerObj = new Timer(userName, timer, date, activity);
                timers.add(timerObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timers;
    }
    private void insertSampleData() {
        addTimer(new Timer("Johnny101", "8.0", "2024-05-21", "Sleeping"));
        addTimer(new Timer("Johnny101", "6.5", "2024-05-22", "Sleeping"));
        addTimer(new Timer("Johnny101", "7.0", "2024-05-23", "Sleeping"));
        addTimer(new Timer("Johnny101", "8.0", "2024-05-24", "Sleeping"));
        addTimer(new Timer("Johnny101", "5.5", "2024-05-25", "Sleeping"));
        addTimer(new Timer("Johnny101", "6.0", "2024-05-26", "Sleeping"));
        addTimer(new Timer("Johnny101", "8.0", "2024-05-21", "workout"));
        addTimer(new Timer("Johnny101", "6.5", "2024-05-22", "reading"));
        addTimer(new Timer("Johnny101", "7.0", "2024-05-23", "workout"));
        addTimer(new Timer("Johnny101", "8.0", "2024-05-24", "reading"));
        addTimer(new Timer("Johnny101", "5.5", "2024-05-25", "workout"));
        addTimer(new Timer("Johnny101", "6.0", "2024-05-26", "reading"));

    }
}

