package com.example.tpathletics.service;

import com.example.tpathletics.entity.Client;
import com.example.tpathletics.entity.Trainer;
import com.example.tpathletics.entity.TrainingPlan;
import com.example.tpathletics.repository.ClientRepository;
import com.example.tpathletics.repository.TrainerRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private EntityManager entityManager;

    /*
    get the list of all clients from the database
     */
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /*
    find a client by his id
     */
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    /*
    save a client to the database
     */
    public void saveClient(Client client) {
        Trainer trainer = entityManager.find(Trainer.class, 1);
        if (trainer != null) {
            client.setTrainer(trainer);
        }
        clientRepository.save(client);
    }


    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }



}
