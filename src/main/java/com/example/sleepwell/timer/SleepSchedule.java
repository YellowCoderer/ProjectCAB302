package com.example.sleepwell.timer;

public class SleepSchedule {
    private String userName;
    private String Monday;
    private String Tuesday;
    private String Wednesday;
    private String Thursday;
    private String Friday;
    private String Saturday;
    private String Sunday;

    public SleepSchedule(String userName, String Monday, String Tuesday, String Wednesday, String Thursday, String Friday, String Saturday, String Sunday) {
        this.userName = userName;
        this.Monday = Monday;
        this.Tuesday = Tuesday;
        this.Wednesday = Wednesday;
        this.Thursday = Thursday;
        this.Friday = Friday;
        this.Saturday = Saturday;
        this.Sunday = Sunday;
    }

    public String getScheduleUsername() {
        return userName;
    }

    public void setScheduleUsername(String userName) {
        this.userName = userName;
    }

    public String getMonday() {
        if (Monday == null) {
            return "18:00";
        }
        return Monday;
    }

    public void setMonday(String Monday) {
        this.Monday = Monday;
    }

    public String getTuesday() {
        if (Tuesday == null) {
            return "18:00";
        }
        return Tuesday;
    }

    public void setTuesday(String Tuesday) {
        this.Tuesday = Tuesday;
    }

    public String getWednesday() {
        if (Wednesday == null) {
            return "18:00";
        }
        return Wednesday;
    }

    public void setWednesday(String Wednesday) {
        this.Wednesday = Wednesday;
    }

    public String getThursday() {
        if (Thursday == null) {
            return "18:00";
        }
        return Thursday;
    }

    public void setThursday(String Thursday) {
        this.Thursday = Thursday;
    }

    public String getFriday() {
        if (Friday == null) {
            return "18:00";
        }
        return Friday;
    }

    public void setFriday(String Friday) {
        this.Friday = Friday;
    }

    public String getSaturday() {
        if (Saturday == null) {
            return "18:00";
        }
        return Saturday;
    }

    public void setSaturday(String Saturday) {
        this.Saturday = Saturday;
    }

    public String getSunday() {
        if (Sunday == null) {
            return "18:00";
        }
        return Sunday;
    }

    public void setSunday(String Sunday) {
        this.Sunday = Sunday;
    }

}
