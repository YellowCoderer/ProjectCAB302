package com.example.sleepwell.timer;

import com.example.sleepwell.database.SqliteConnection;

import java.sql.*;
import java.util.List;

public class SqliteSleepScheduleDAO implements ISleepScheduleDAO{
    private Connection connection = SqliteConnection.getInstance();

    public SqliteSleepScheduleDAO() {
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS schedule ("
                    + "userid INTEGER PRIMARY KEY,"
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
    @Override
    public void addSleepSchedule(Integer id, SleepSchedule sleepschedule) {
        try {
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT IGNORE INTO schedule (userid, monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES ('" +
                    "Empty" + "', '" +
                    "Empty" + "', '" +
                    "Empty" + "', '" +
                    "Empty" + "', '" +
                    "Empty" + "', '" +
                    "Empty" + "', '" +
                    "Empty" + "', '" +
                    "Empty" + "')";
            statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSleepSchedule(Integer id, SleepSchedule sleepschedule) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE schedule SET monday = ?, tuesday = ?, wednesday = ?, thursday = ?, friday = ?, saturday = ?, sunday = ?, WHERE userid = ?");
            statement.setString(1, sleepschedule.getMonday());
            statement.setString(2, sleepschedule.getTuesday());
            statement.setString(3, sleepschedule.getWednesday());
            statement.setString(4, sleepschedule.getThursday());
            statement.setString(5, sleepschedule.getFriday());
            statement.setString(5, sleepschedule.getSaturday());
            statement.setString(5, sleepschedule.getSunday());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSleepSchedule(SleepSchedule sleepschedule) {

    }

    @Override
    public SleepSchedule getSleepSchedule(int scheduleid) {
        String query = "SELECT * FROM schedule WHERE userid = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, scheduleid);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("userid");
                String Monday = resultSet.getString("monday");
                String Tuesday = resultSet.getString("tuesday");
                String Wednesday = resultSet.getString("wednesday");
                String Thursday = resultSet.getString("thursday");
                String Friday = resultSet.getString("friday");
                String Saturday = resultSet.getString("saturday");
                String Sunday = resultSet.getString("sunday");

                SleepSchedule sleepschedule = new SleepSchedule(id, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday);
                return (SleepSchedule) sleepschedule;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<SleepSchedule> getAllSleepSchedule(int id) {
        return null;
    }
}
