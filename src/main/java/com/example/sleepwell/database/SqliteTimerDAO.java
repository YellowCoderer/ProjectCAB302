package com.example.sleepwell.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class SqliteTimerDAO implements ITimerDAO{
    private Connection connection = SqliteConnection.getInstance();
    public SqliteTimerDAO(){
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS timers ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
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
            String insertQuery = "INSERT INTO timers (id, timer, date, activity) VALUES ('" +
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
    public Timer getTimer(int timerid) {

        String query = "SELECT * FROM timers WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, timerid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Float timer = resultSet.getFloat("timer");
                Date date = resultSet.getDate("date");
                String activity = resultSet.getString("activity");

                Timer timer1 = new Timer(timer, date, activity);
                return timer1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Setting> getAllTimer() {
        return null;
    }
}
