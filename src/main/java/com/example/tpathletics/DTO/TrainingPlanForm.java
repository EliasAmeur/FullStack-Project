package com.example.tpathletics.DTO;

import java.util.ArrayList;
import java.util.List;



/*
Data Transfer Object (DTO) to simplify communication between the spring app and the database when creating a new training program
 */

public class TrainingPlanForm {
    private List<ExerciseForm> exercises = new ArrayList<>();

    // Getters and setters
    public List<ExerciseForm> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseForm> exercises) {
        this.exercises = exercises;
    }
}
