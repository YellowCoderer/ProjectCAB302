package com.example.sleepwell.timer;

import javafx.collections.ObservableList;

public interface ITimerDAO {
    public void addTimer(Timer timer);

    public Timer getTimer(String userName);

    public ObservableList<Timer> getAllTimers(String userName);
}
