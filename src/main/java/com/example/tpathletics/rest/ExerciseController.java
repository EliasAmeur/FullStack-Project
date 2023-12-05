package com.example.tpathletics.rest;

import com.example.tpathletics.entity.Exercise;
import com.example.tpathletics.service.ExerciseService;
import com.example.tpathletics.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/trainer")
public class ExerciseController {

    /*
    RestAPI to communicate the information from the database to the Spring application for the exercises that are added to a training program
     */

    private final ExerciseService exerciseService;
    private final ImageStorageService imageStorageService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ImageStorageService imageStorageService) {
        this.exerciseService = exerciseService;
        this.imageStorageService = imageStorageService;
    }

    @PostMapping("/exercises/new")
    public String saveExercise(@ModelAttribute Exercise exercise, @RequestParam("imageUrl") MultipartFile imageFile) {
        String imageUrl = imageStorageService.saveImage(imageFile); // Handle image saving
        exercise.setImageUrl(imageUrl);
        exerciseService.saveExercise(exercise);
        return "redirect:/trainer/exercises";
    }


}
