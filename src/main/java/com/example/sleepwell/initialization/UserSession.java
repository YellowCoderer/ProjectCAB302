package com.example.sleepwell.initialization;

import java.lang.annotation.Target;

public final class UserSession {
    // Code below taken from: Berezhnoi, T. (2017). How to keep user information after login in JavaFX desktop application [Answer to forum post]. Stack Overflow. https://stackoverflow.com/questions/46508098/how-to-keep-user-information-after-login-in-javafx-desktop-application
    // To keep user information after login, we are using the concept of singleton design patter
    private static UserSession instance;
    private String username;
    private Integer userId;

    private UserSession(String username, Integer userId) {
        this.username = username;
        this.userId = userId;
    }

    public static synchronized void initialize(String username, Integer userId) {
        if (instance == null) {
            instance = new UserSession(username, userId);
        } else {
            System.out.println("Instance already exists, not creating new.");
        }
    }

    public static UserSession getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance not yet created. Please call initialize with parameters first.");
        }
        return instance;
    }

    public static void cleanUserSession() {
        if (instance != null) {
            instance.username = null;
            instance.userId = null;
            instance = null; // Ensure instance is also nullified
        }
    }

    public String getUsername() {
        return username;
    }

    public Integer getUserId() {
        return userId;
    }

    // Set users' brightness preference
    public static double brightness = 0.0; // Default scene brightness (min: -1, max: 0)

    public static void setBrightness(double newBrightness) {
        brightness = newBrightness;
    }

    public static double getBrightness() {
        return brightness;
    }
}