package com.example.sleepwell.database;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class SqliteAccountDAO implements IAccountsDAO{


    /** creates table to the database*/
    private Connection connection = SqliteConnection.getInstance();

    public SqliteAccountDAO(){
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS accounts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL"
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
            String insertQuery = "INSERT INTO accounts (firstName, lastName, password, email) VALUES "
                    + "('John', 'Doe', 'password', 'johndoe@example.com'),"
                    + "('Jane', 'Doe', 'password1', 'janedoe@example.com'),"
                    + "('Jay', 'Doe', 'password2', 'jaydoe@example.com')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAccount(Accounts accounts) {
    }

    @Override
    public void updateAccount(Accounts accounts) {

    }

    @Override
    public void deleteAccount(Accounts accounts) {

    }

    @Override
    public Accounts getAccount(int id) {
        return null;
    }

    @Override
    public List<Accounts> getAllContacts() {
        return null;
    }
}