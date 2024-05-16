package com.example.sleepwell.database;

public class Timer {
    private int id;
    private long timer;
    private long date;
    private String activity;

    public Timer(int id, long timer, long date, String activity) {
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
    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    /**receives and sets Date*/
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

}
