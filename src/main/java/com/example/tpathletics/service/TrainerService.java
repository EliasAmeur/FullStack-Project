package com.example.tpathletics.service;

import com.example.tpathletics.entity.Trainer;
import com.example.tpathletics.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository){
        this.trainerRepository=trainerRepository;
    }

    public Trainer getTrainerById(String id){
        return trainerRepository.findById(id).orElse(null);
    }
}
