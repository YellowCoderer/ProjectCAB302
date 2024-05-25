package com.example.sleepwell.timer;

import com.example.sleepwell.database.Accounts;
import com.example.sleepwell.database.SqliteConnection;

import java.sql.*;
import java.util.List;

public class SqliteSleepScheduleDAO implements ISleepScheduleDAO{
    private Connection connection = SqliteConnection.getInstance();

    public SqliteSleepScheduleDAO() {
        createTable();
        //insertSampleData();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS schedule ("
                    + "username VARCHAR NOT NULL PRIMARY KEY,"
                    + "monday STRING NOT NULL,"
                    + "tuesday STRING NOT NULL,"
                    + "wednesday STRING NOT NULL,"
                    + "thursday STRING NOT NULL,"
                    + "friday STRING NOT NULL,"
                    + "saturday STRING NOT NULL,"
                    + "sunday STRING NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**creates a sample table of the database*/
    private void insertSampleData() {
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM accounts";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO schedule (username, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES "
                    + "('Johnny101', '21:00', '21:00', '21:30', '21:30', '21:30', '21:30', '21:30'),"
                    + "('Jane123', '20:00', '21:00', '21:00', '20:00', '8:30', '21:00', '21:00'),"
                    + "('JayZ_9', '22:00', '18:00', '22:00', '18:00', '18:00', '22:00', '21:30')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSleepSchedule(SleepSchedule sleepschedule) {
        try {
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT OR IGNORE INTO schedule (userName, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES ('" +
                    sleepschedule.getScheduleUsername() + "', '" +
                    sleepschedule.getMonday() + "', '" +
                    sleepschedule.getTuesday() + "', '" +
                    sleepschedule.getWednesday() + "', '" +
                    sleepschedule.getThursday() + "', '" +
                    sleepschedule.getFriday() + "', '" +
                    sleepschedule.getSaturday() + "', '" +
                    sleepschedule.getSunday() + "')";
            statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSleepSchedule(String scheduleUsername, SleepSchedule sleepschedule) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE schedule SET monday = ?, tuesday = ?, wednesday = ?, thursday = ?, friday = ?, saturday = ?, sunday = ? WHERE userName = ?");
            statement.setString(1, sleepschedule.getMonday());
            statement.setString(2, sleepschedule.getTuesday());
            statement.setString(3, sleepschedule.getWednesday());
            statement.setString(4, sleepschedule.getThursday());
            statement.setString(5, sleepschedule.getFriday());
            statement.setString(6, sleepschedule.getSaturday());
            statement.setString(7, sleepschedule.getSunday());
            statement.setString(8, sleepschedule.getScheduleUsername());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSleepSchedule(SleepSchedule sleepschedule) {

    }

    @Override
    public SleepSchedule getSleepSchedule(String scheduleUsername) {
        String query = "SELECT * FROM schedule WHERE userName = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, scheduleUsername);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String Monday = resultSet.getString("monday");
                String Tuesday = resultSet.getString("tuesday");
                String Wednesday = resultSet.getString("wednesday");
                String Thursday = resultSet.getString("thursday");
                String Friday = resultSet.getString("friday");
                String Saturday = resultSet.getString("saturday");
                String Sunday = resultSet.getString("sunday");

                SleepSchedule sleepschedule = new SleepSchedule(userName, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday);
                return (SleepSchedule) sleepschedule;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SleepSchedule> getAllSleepSchedule() {
        return null;
    }

}
