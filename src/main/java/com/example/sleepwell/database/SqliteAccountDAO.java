package com.example.sleepwell.database;

import java.sql.*;
import java.util.List;

public class SqliteAccountDAO implements IAccountsDAO{
    /** creates table to the database*/
    private final Connection connection = SqliteConnection.getInstance();

    public SqliteAccountDAO(){
        createTable();
      //  insertSampleData();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS accounts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "userName VARCHAR NOT NULL,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL,"
                    + "phone VARCHAR NOT NULL,"
                    + "image VARCHAR DEFAULT 'Account.png'"
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
            String insertQuery = "INSERT INTO accounts (username, firstName, lastName, password, email, phone) VALUES "
                    + "('Johnny101', 'John', 'Doe', 'password', 'johndoe@example.com', '0423423425'),"
                    + "('Jane123', 'Jane', 'Doe', 'password1', 'janedoe@example.com', '0423423427'),"
                    + "('JayZ_9', 'Jay', 'Doe', 'password2', 'jaydoe@example.com', '0423423429')";
            insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAccount(Accounts account) {
        try {
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO accounts (username, firstName, lastName, email, phone, password) VALUES ('" +
                    account.getUsername() + "', '" +
                    account.getFirstName() + "', '" +
                    account.getLastName() + "', '" +
                    account.getEmail() + "', '" +
                    account.getPhone() + "', '" +
                    account.getPassword() + "')";
            statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Integer id, Accounts accounts) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET username = ?, firstName = ?, lastName = ?, phone = ?, email = ? WHERE id = ?");
            statement.setString(1, accounts.getUsername());
            statement.setString(2, accounts.getFirstName());
            statement.setString(3, accounts.getLastName());
            statement.setString(4, accounts.getPhone());
            statement.setString(5, accounts.getEmail());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateImage(Integer id, String image) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET image = ? WHERE id = ?");
            statement.setString(1, image);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Accounts accounts) {
    }

    /** create an account from a ResultSet */
    private Accounts createAccountFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String password = resultSet.getString("password");
        String image = resultSet.getString("image");

        Accounts account = new Accounts(username, firstName, lastName, email, phone, password);
        account.setId(id);
        account.setImage(image);
        return account;
    }

    /**Generic method to get an account by a parameter*/
    private Accounts getAccountByParameter(String query, String parameter) {
        String url = "jdbc:sqlite:SleepWellDB.db";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, parameter);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createAccountFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Accounts getAccount(int id) {
        String query = "SELECT * FROM accounts WHERE id = ?";
        return getAccountByParameter(query, Integer.toString(id));
    }

    @Override
    public Accounts getAccountWithEmail(String email) {
        String query = "SELECT * FROM accounts WHERE email = ?";
        return getAccountByParameter(query, email);
    }

    @Override
    public Accounts getAccountWithUsername(String username) {
        String query = "SELECT * FROM accounts WHERE username = ?";
        return getAccountByParameter(query, username);
    }

    @Override
    public List<Accounts> getAllContacts() {
        return null;
    }
}