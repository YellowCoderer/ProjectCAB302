package com.example.sleepwell.database;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteGoalsDAO implements IGoalsDAO {
    private Connection connection;
    public static final ArrayList<Goals> goalsql = new ArrayList<>();
    private static int autoIncrementedId = 0;
    public SqliteGoalsDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
        // Used for testing, to be removed later
        insertSampleData();
    }

    private void insertSampleData() {
        try {
            // Clear before inserting
            Statement clearStatement = connection.createStatement();
            String clearQuery = "DELETE FROM goalsql";
            clearStatement.execute(clearQuery);
            Statement insertStatement = connection.createStatement();
            String insertQuery = "INSERT INTO goalsql (newgoals,) VALUES "
                    + "('sleep 8 hours'),"
                    + "('no computer before bed'),"
                    + "('sleep in')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS goalsql ("
                + "goalsql VARCHAR NOT NULL,"
                + ")";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addGoals(Goals goals) {
        goals.setId(autoIncrementedId);
        autoIncrementedId++;
        goalsql.add(goals);
    }

    @Override
    public void updateGoals(Goals goals) {
        for (int i = 0; i < goalsql.size(); i++) {
        if (goalsql.get(i).getId() == goals.getId()) {

            goalsql.set(i, goals);
            break;
        }
    }

    }

    @Override
    public void deleteGoal(Goals goals) {
        goalsql.remove(goals);
    }



    @Override
    public Goals getGoals(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM goalsql WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String NewGoal = resultSet.getString("goals");
                Goals goals = new Goals(NewGoal);
                goals.setId(id);
                return goals;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Goals> getAllGoals() {
        List<Goals> goalsql = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM goalsql";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String NewGoal = resultSet.getString("NewGoal");
                Goals goals = new Goals(NewGoal);
                goals.setId(id);
                goalsql.add(goals);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goalsql;

    }


}
