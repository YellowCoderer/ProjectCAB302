package com.example.sleepwell.database;
/**
 * A simple model class representing a contact with a username, first name, last name, email, phone number and password.
 * */

public class Accounts {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;

    /**
     * Constructs a new Contact with the specified first name, last name, email, and phone number.
     * @param username The username of the contact
     * @param firstName The first name of the contact
     * @param lastName The last name of the contact
     * @param email The email of the contact
     * @param phone The phone number of the contact
     * @param password The password of the contact
     */

    public Accounts(String username, String firstName, String lastName, String email, String phone, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    /**receives and sets id*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**receives and sets username name*/
    public String getUsername() {
        return username;
    }

    public void setUsername(String firstName) {
        this.username = username;
    }

    /**receives and sets first name*/
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**receives and sets last nae*/
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**receives and sets email*/
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**receives and sets phone number*/
    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    /**receives and sets password*/
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**receiving full name*/
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
