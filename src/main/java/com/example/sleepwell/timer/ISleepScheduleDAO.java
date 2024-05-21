package com.example.sleepwell.timer;

import java.util.List;

public interface ISleepScheduleDAO {
    public void addSleepSchedule(Integer id, SleepSchedule sleepschedule);

    public void updateSleepSchedule(Integer id, SleepSchedule sleepschedule);

    public void deleteSleepSchedule(SleepSchedule sleepschedule);

    public SleepSchedule getSleepSchedule(int id);

    public List<SleepSchedule> getAllSleepSchedule(int id);
}
