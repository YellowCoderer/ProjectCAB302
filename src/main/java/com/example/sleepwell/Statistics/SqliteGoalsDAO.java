package com.example.sleepwell.Statistics;

import com.example.sleepwell.database.SqliteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteGoalsDAO implements IGoalsDao {
    private Connection connection = SqliteConnection.getInstance();

    public SqliteGoalsDAO() {
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS goals ("
                    + "username TEXT NOT NULL,"
                    + "goal TEXT NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveGoalInDatabase(Goals goal) {
        String query = "INSERT INTO goals(username, goal) VALUES(?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, goal.getGoalsuserName());
            pstmt.setString(2, goal.getGoalsGoal());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGoalFromDatabase(String goal) {
        String query = "DELETE FROM goals WHERE goal = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, goal);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getGoalsForUser(String username) {
        List<String> goals = new ArrayList<>();
        String query = "SELECT goal FROM goals WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                goals.add(rs.getString("goal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals;
    }
}
