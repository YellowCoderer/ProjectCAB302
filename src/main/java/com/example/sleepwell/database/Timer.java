package com.example.sleepwell.database;

import java.util.Date;

public class Timer {
    private int id;
    private String timer;
    private String date;
    private String activity;

    public Timer(int id, String timer, String date, String activity) {
        this.id = id;
        this.timer = timer;
        this.date = date;
        this.activity = activity;
    }
    /**receives and sets id*/
    public int getTimerId() {
        return id;
    }

    public void setTimerId(int id) {
        this.id = id;
    }

    /**receives and sets Activity*/
    public String getActivity() {return activity;}

    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**receives and sets Timer*/
    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    /**receives and sets Date*/
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
