package com.example.sleepwell.timer;

public class SleepSchedule {
    private int id;
    private String Monday;
    private String Tuesday;
    private String Wednesday;
    private String Thursday;
    private String Friday;
    private String Saturday;
    private String Sunday;
    public SleepSchedule(int id, String Monday, String Tuesday, String Wednesday, String Thursday, String Friday, String Saturday, String Sunday) {
        this.id = id;
        this.Monday = Monday;
        this.Tuesday = Tuesday;
        this.Wednesday = Wednesday;
        this.Thursday = Thursday;
        this.Friday = Friday;
        this.Saturday = Saturday;
        this.Sunday = Sunday;
    }

    public int getSleepScheduleId() {
        return id;
    }

    public void setSleepScheduleId(int id) {
        this.id = id;
    }

    public String getMonday() {return Monday;}

    public void setMonday(String Monday) {
        this.Monday = Monday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    public void setTuesday(String Tuesday) {
        this.Tuesday = Tuesday;
    }

    public String getWednesday() {
        return Wednesday;
    }

    public void setWednesday(String Wednesday) {
        this.Wednesday = Wednesday;
    }

    public String getThursday() {
        return Thursday;
    }

    public void setThursday(String Thursday) {
        this.Thursday = Thursday;
    }

    public String getFriday() {
        return Friday;
    }

    public void setFriday(String Friday) {
        this.Friday = Friday;
    }

    public String getSaturday() {
        return Saturday;
    }

    public void setSaturday(String Saturday) {
        this.Saturday = Saturday;
    }

    public String getSunday() {
        return Sunday;
    }

    public void setSunday(String Sunday) {
        this.Sunday = Sunday;
    }

}
