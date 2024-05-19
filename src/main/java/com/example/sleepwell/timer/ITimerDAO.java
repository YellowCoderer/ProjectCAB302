package com.example.sleepwell.timer;

import java.util.List;

public interface ITimerDAO {
    public void addTimer(Timer timer);

    public void updateTimer(Timer timer);

    public void deleteTimer(Timer timer);

    public Timer getTimer(int id);

    public List<Timer> getAllTimer(int id);
}
