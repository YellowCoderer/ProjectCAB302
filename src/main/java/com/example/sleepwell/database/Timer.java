package com.example.sleepwell.database;

import java.util.Date;

public class Timer {
    private int id;
    private float timer;
    private Date date;
    private String activity;

    public Timer(float timer, Date date, String activity) {
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
    public float getTimer() {
        return timer;
    }

    public void setTimer(float timer) {
        this.timer = timer;
    }

    /**receives and sets Date*/
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
