package com.example.sleepwell.timer;

import java.util.List;

public interface ISleepScheduleDAO {
    public void addSleepSchedule(SleepSchedule sleepschedule);

    public void updateSleepSchedule(String scheduleUsername, SleepSchedule sleepschedule);

    public void deleteSleepSchedule(SleepSchedule sleepschedule);

    public SleepSchedule getSleepSchedule(String scheduleUsername);

    public List<SleepSchedule> getAllSleepSchedule();
}
