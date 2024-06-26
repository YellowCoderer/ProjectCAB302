package com.example.sleepwell.database;
/**values for the accounts*/
public class Accounts {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String image;

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

    public void setUsername(String username) {
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

    /**receives and sets phone number*/
    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }
}
