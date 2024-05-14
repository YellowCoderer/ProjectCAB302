package com.example.sleepwell.database;
import java.util.List;
public interface IAccountsDAO {
    /**
     * adds the account
     * @param accounts The contact to add.
     */
    public void addAccount(Accounts accounts);
    /**
     * update the account
     * @param id The id of user to update.
     * @param accounts The account to update.
     */
    public void updateAccount(Integer id, Accounts accounts);
    /**
     * changes the users' profile picture
     * @param id The id of user to update image.
     * @param image Set chosen image to update.
     */
    public void updateImage(Integer id, String image);
    /**
     * Deletes a contact from the database.
     * @param accounts The account to delete.
     */
    public void deleteAccount(Accounts accounts);
    /**
     * Retrieves an account from the database via id.
     * @param id The id of the contact to retrieve.
     * @return The account with the given id, or null if not found.
     */
    public Accounts getAccount(int id);
    /**
     * Retrieves an account from the database via email.
     * @param email The email of the contact to retrieve.
     * @return The account with the given email, or null if not found.
     */
    public Accounts getAccountWithEmail(String email);
    /**
     * Retrieves an account from the database via username.
     * @param username The username of the contact to retrieve.
     * @return The account with the given username, or null if not found.
     */
    public Accounts getAccountWithUsername(String username);
    /**
     * Retrieves all accounts from the database.
     * @return A list of all accounts in the database.
     */
    public List<Accounts> getAllContacts();
}
