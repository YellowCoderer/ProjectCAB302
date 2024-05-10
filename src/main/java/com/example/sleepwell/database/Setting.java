package com.example.sleepwell.database;

import java.util.Date;

public class Setting {
    private int id;
    private float brightness;
    public Setting(float brightness) {
        this.brightness = brightness;
    }
    public int getSettingId() {
        return id;
    }
    public void setSettingId(int id) {
        this.id = id;
    }
    public float getBrightness() {return brightness;}
    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }
}
