package com.example.sleepwell.database;

import java.util.Date;

public class Detail {
    private int id;
    private float AmountofSleep;
    public Detail(float AmountofSleep) {
        this.AmountofSleep = AmountofSleep;
    }
    public int getDetailId() {
        return id;
    }
    public void setDetailId(int id) {
        this.id = id;
    }
    public float getAmountofSleep() {return AmountofSleep;}
    public void setAmountofSleep(String activity) {
        this.AmountofSleep = AmountofSleep;
    }

}
