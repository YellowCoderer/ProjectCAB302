package com.example.sleepwell.database;

import java.util.List;

public interface ITimerDAO {
    public void addTimer(Timer timer);

    public void updateTimer(Timer timer);

    public void deleteTimer(Timer timer);

    public Timer getTimer(int timerid);

    public List<Timer> getAllTimer(int timerid);
}
