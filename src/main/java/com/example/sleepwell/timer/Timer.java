package com.example.sleepwell.timer;

public class Timer {
    private int id;
    private String time;
    private String date;
    private String activity;

    public Timer(int id, String time, String date, String activity) {
        this.id = id;
        this.time = time;
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
        return time;
    }

    public void setTimer(String time) {
        this.time = time;
    }

    /**receives and sets Date*/
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
