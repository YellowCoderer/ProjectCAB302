package com.example.sleepwell.database;

//reference: https://stackoverflow.com/questions/46508098/how-to-keep-user-information-after-login-in-javafx-desktop-application
public final class UserSession {
    private static UserSession instance;
    private String userName;
    private Integer userId;

    private UserSession(String userName, Integer userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public static UserSession getInstance(String userName, Integer userId) {
        if (instance == null) {
            instance = new UserSession(userName, userId);
        } else {
            System.out.println("Instance already exists, not creating new.");
        }
        return instance;
    }

    public static UserSession getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Instance not yet created. Please call getInstance with parameters first.");
        }
        return instance;
    }

    public static void cleanUserSession() {
        if (instance != null) {
            instance.userName = null;
            instance.userId = null;
            instance = null; // Ensure instance is also nullified
        }
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public Integer userId() {
        return userId;
    }

    //Brightness Preferences
    public static double brightness = 0.0; // Default brightness

    public static void setBrightness(double newBrightness) {
        brightness = newBrightness;
    }

    public static double getBrightness() {
        return brightness;
    }
}