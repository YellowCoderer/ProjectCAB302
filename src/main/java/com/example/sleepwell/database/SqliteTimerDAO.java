package com.example.sleepwell.database;

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
        try {
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO timers (userid, timer, date, activity) VALUES ('" +
                    timer.getTimerId() + "', '" +
                    timer.getTimer() + "', '" +
                    timer.getDate() + "', '" +
                    timer.getActivity() + "')";
            statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateTimer(Timer timer) {

    }

    @Override
    public void deleteTimer(Timer timer) {

    }

    @Override
    public Timer getTimer(int id) {

        return null;
    }

    @Override
    public List<Timer> getAllTimer(int timerid) {
        String query = "SELECT * FROM timers WHERE userid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, timerid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("userid");
                String timer = resultSet.getString("timer");
                String date = resultSet.getString("date");
                String activity = resultSet.getString("activity");

                Timer timer1 = new Timer(id, timer, date, activity);
                return (List<Timer>) timer1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
