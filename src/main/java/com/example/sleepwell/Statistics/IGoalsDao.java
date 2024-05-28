package com.example.sleepwell.Statistics;

import java.util.List;

public interface IGoalsDao {
    void saveGoalInDatabase(Goals goal);

    void deleteGoalFromDatabase(String goal);

    List<String> getGoalsForUser(String username);
}
