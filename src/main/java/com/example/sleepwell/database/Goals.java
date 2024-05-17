package com.example.sleepwell.database;

public class Goals {
        private String NewGoal;
        private int id;

        public Goals(String NewGoal) {
            this.NewGoal = NewGoal;
        }
    public String getNewGoal() {
        return NewGoal;
    }

    public void setNewGoal(String NewGoal) {
        this.NewGoal = NewGoal;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
