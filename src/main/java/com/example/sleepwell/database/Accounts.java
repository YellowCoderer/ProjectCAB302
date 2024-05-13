package com.example.sleepwell.database;

import java.util.Date;

/**values for the accounts*/
public class Accounts {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    /** values for timer page*/

    /** values for detail page*/

    /** values for setting page*/



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
