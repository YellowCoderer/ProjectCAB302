package com.example.sleepwell.database;
import java.util.List;
public interface IAccountsDAO {
    /**adds the account*/
    public void addAccount(Accounts accounts);

    /**update the account*/
    public void updateAccount(Accounts accounts);

    /** deletes the account*/
    public void deleteAccount(Accounts accounts);
    /**
     * Retrieves a account from database using id
     */
    public Accounts getAccount(int id);
    /**
     * Retrieves all account from the database.
     */
    public List<Accounts> getAllContacts();

    public Accounts getAccountWithEmail(String email);
}
