package com.example.tpathletics.DTO;

import org.springframework.web.multipart.MultipartFile;

/*
Data Transfer Object (DTO) to simplify communication between the spring app and the database when creating new exercises
 */
public class ExerciseForm {
    private String name;
    private MultipartFile imageFile;
    private Integer sets;
    private Integer reps;
    private Integer duration;
    private String notes;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Getters and setters
}
