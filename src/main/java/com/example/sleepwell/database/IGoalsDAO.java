package com.example.sleepwell.database;
import java.util.List;

public interface IGoalsDAO {
    public void addGoals(Goals goals);

    public void updateGoals(Goals goals);

    public void deleteGoal(Goals goals);
    public Goals getGoals(int id);


    public  List<Goals> getAllGoals();}

