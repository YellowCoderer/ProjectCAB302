package com.example.sleepwell.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SqliteTimerDAO implements ITimerDAO{
    private Connection connection = SqliteConnection.getInstance();
    public SqliteTimerDAO(){
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS accounts ("
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

    }

    @Override
    public void updateTimer(Timer timer) {

    }

    @Override
    public void deleteTimer(Timer timer) {

    }

    @Override
    public Detail getTimer(int timerid) {
        return null;
    }

    @Override
    public List<Setting> getAllTimer() {
        return null;
    }
}
