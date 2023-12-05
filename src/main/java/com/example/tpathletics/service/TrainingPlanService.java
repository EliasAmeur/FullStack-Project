package com.example.tpathletics.service;

import com.example.tpathletics.entity.Client;
import com.example.tpathletics.entity.Exercise;
import com.example.tpathletics.entity.TrainingPlan;
import com.example.tpathletics.repository.ClientRepository;
import com.example.tpathletics.repository.ExerciseRepository;
import com.example.tpathletics.repository.TrainingPlanRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingPlanService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TrainingPlanRepository trainingPlanRepository;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Transactional
    public TrainingPlan saveTrainingPlan(Long idClient, List<Exercise> exercises) {
        Client client = clientRepository.findById(idClient).orElse(null);
        if (client != null) {

            // Check if the client already has a training plan
            TrainingPlan oldTrainingPlan = client.getTrainingProgram();
            if (oldTrainingPlan != null) {
                // Delete old exercises and training plan
                exerciseRepository.deleteAll(oldTrainingPlan.getExercises());
                trainingPlanRepository.delete(oldTrainingPlan);
            }


            //Save new training plan
            TrainingPlan program = new TrainingPlan();
            program.setExercises(exercises);
            trainingPlanRepository.save(program); //  save the training plan

            client.setTrainingProgram(program); // Then, link the plan to the client
            clientRepository.save(client); // Finally, save the client
            return program;
        }

        return null;
    }

    public List<Exercise> getAllExercise(Long idClient){
        Client client = clientRepository.findById(idClient).orElse(null);
        assert client != null;
        TrainingPlan trainingPlan = client.getTrainingProgram();
        return trainingPlan.getExercises();
    }

    @Transactional
    public void updateTrainingPlan(TrainingPlan trainingPlan) {
        trainingPlanRepository.save(trainingPlan);
    }
}
