package com.example.sleepwell.database;
/**values for the accounts*/
public class Accounts {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Accounts(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = phone;
    }
    /**receives and sets id*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
