package com.example.sleepwell.Statistics;

public class Goals {
    private static String userName;
    private static String Goal;

    public Goals(String userName, String Goal) {
        Goals.userName = userName;
        Goals.Goal = Goal;

    }
    public static String getGoalsuserName(){
        return userName;
    }
    public static String getGoalsGoal(){
        return Goal;
    }
    public void SetGoalsGoal (String Goal){
        Goals.Goal = Goal;
    }

}

