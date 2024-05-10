package com.example.sleepwell.database;

import java.util.List;

public interface ITimerDAO {
    public void addTimer(Timer timer);

    public void updateTimer(Timer timer);

    public void deleteTimer(Timer timer);

    public Detail getTimer(int timerid);

    public List<Setting> getAllTimer();
}
