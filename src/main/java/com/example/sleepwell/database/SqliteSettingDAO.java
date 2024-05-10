package com.example.sleepwell.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SqliteSettingDAO implements ISettingDAO{
    private Connection connection = SqliteConnection.getInstance();
    public SqliteSettingDAO(){
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS accounts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "brightness FLOAT NOT NULL,"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSetting(Setting setting) {

    }

    @Override
    public void updateSetting(Setting setting) {

    }

    @Override
    public void deleteSetting(Setting setting) {

    }

    @Override
    public Detail getSetting(int settingid) {
        return null;
    }

    @Override
    public List<Setting> getAllSetting() {
        return null;
    }
}
