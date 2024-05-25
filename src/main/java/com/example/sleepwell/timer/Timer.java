package com.example.sleepwell.timer;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Timer extends RecursiveTreeObject<Timer> {
    private String  userName;
    private StringProperty timer;
    private StringProperty date;
    private StringProperty activity;

    public Timer(String userName, String timer, String date, String activity) {
        this.userName = userName;
        this.timer = new SimpleStringProperty(timer);
        this.date = new SimpleStringProperty(date);
        this.activity = new SimpleStringProperty(activity);
    }

    // Constructor without the userName
    public Timer(String timer, String date, String activity) {
        this("", timer, date, activity); // Call the main constructor with an empty username
    }

    /**receives and sets id*/
    public String getTimerId() {
        return userName;
    }

    public void setTimerId(String userName) {
        this.userName = userName;
    }

    // Activity accessors
    public StringProperty activityProperty() { // Renamed for JavaFX conventions
        return activity;
    }

    /**receives and sets Activity*/
    public StringProperty getActivity() {return activity;}

    public void setActivity(StringProperty activity) {
        this.activity = activity;
    }

    // Timer accessors
    public StringProperty timerProperty() { // Renamed for JavaFX conventions
        return timer;
    }

    /**receives and sets Timer*/
    public StringProperty getTimer() {
        return timer;
    }

    public void setTimer(StringProperty timer) {
        this.timer = timer;
    }

    // Date accessors
    public StringProperty dateProperty() { // Renamed for JavaFX conventions
        return date;
    }

    /**receives and sets Date*/
    public StringProperty getDate() {
        return date;
    }

    public void setDate(StringProperty date) {
        this.date = date;
    }
}
