package com.example.sleepwell.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
public class SqliteDetailDAO implements IDetailDAO{
    private Connection connection = SqliteConnection.getInstance();

    public SqliteDetailDAO(){
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS accounts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "AmountofSleep FLOAT NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addDetail(Detail detail) {

    }

    @Override
    public void updateDetail(Detail detail) {

    }

    @Override
    public void deleteDetail(Detail detail) {

    }

    @Override
    public Detail getDetail(int id) {
        return null;
    }

    @Override
    public List<Detail> getAllDetail() {
        return null;
    }
}
