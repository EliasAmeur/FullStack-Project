package com.example.tpathletics.service;

import com.example.tpathletics.entity.Client;
import com.example.tpathletics.entity.Trainer;
import com.example.tpathletics.repository.ClientRepository;
import com.example.tpathletics.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    /*
    Authenticates the Trainer when logging in with its username and password stored in the database.
     */

    @Autowired
    private TrainerRepository trainerRepository;


    public Trainer authenticateTrainer(String email, String password) {
        Trainer trainer = trainerRepository.findByEmail(email);
        if (trainer != null && trainer.getPassword().equals(password)) {
            return trainer;
        }
        return null;
    }

}
