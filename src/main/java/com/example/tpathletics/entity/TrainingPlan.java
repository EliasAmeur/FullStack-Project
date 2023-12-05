package com.example.tpathletics.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="training_plan")
public class TrainingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProgramme;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "trainingPlan")
    private List<Exercise> exercises;

    public TrainingPlan(String name, List<Exercise> exercises) {
        this.name = name;
        this.exercises = exercises;
    }


    public TrainingPlan() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public long getIdProgramme() {
        return idProgramme;
    }

    public void setIdProgramme(long idProgramme) {
        this.idProgramme = idProgramme;
    }

}
