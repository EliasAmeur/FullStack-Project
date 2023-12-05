package com.example.tpathletics.controller;

import com.example.tpathletics.DTO.ExerciseForm;
import com.example.tpathletics.DTO.TrainingPlanForm;
import com.example.tpathletics.entity.Client;
import com.example.tpathletics.entity.Exercise;
import com.example.tpathletics.entity.Trainer;
import com.example.tpathletics.entity.TrainingPlan;
import com.example.tpathletics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainerController {

    @Autowired
    private ClientService clientService; // Service to interact with client data
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private TrainingPlanService trainingPlanService;

    /*
    this method retrieves a list of all clients
     */
    @GetMapping("/trainer/clients")
    public String viewClients(Model model) {
        List<Client> clients = clientService.getAllClients(); // Retrieve all clients
        model.addAttribute("clients", clients); // Add clients to the model
        return "ClientList"; // Name of the Thymeleaf template
    }

    /*
    this method opens the Trainer Hub, where the trainer can add and modify clients and training programs
     */
    @GetMapping("/trainer")
    public String hub(){
        return "TrainingApp";
    }

    /*
    this opens the page to add new clients
     */
    @GetMapping("/trainer/clients/new")
    public String formulaireClient(Model model){
        Client client = new Client();

        model.addAttribute("client", client);
        model.addAttribute("pageTitle", "Add new client");
        return "clientForm";
    }

    /*
    this saves the new client to the database
     */
    @PostMapping("/trainer/clients/new")
    public String saveClient(@ModelAttribute("client") Client client, RedirectAttributes redirectAttributes) {
        clientService.saveClient(client); // Save the client using your service
        redirectAttributes.addFlashAttribute("message", "Client saved successfully!");
        return "redirect:/trainer/clients"; // Redirect to the list of clients
    }

    /*
    this opens the edit page to modify a client's info
     */
    @GetMapping("/trainer/clients/edit/{idClient}")
    public String showEditForm(@PathVariable("idClient") long id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "clientForm";
    }

/*
this modify's the client's information in the database
 */
    @PostMapping("/trainer/clients/edit/{idClient}")
    public String updateClient(@PathVariable("idClient") long id, @ModelAttribute("client") Client client) {
        Client existingClient = clientService.getClientById(id);

        if (existingClient != null) {
            existingClient.setFirstName(client.getFirstName());
            existingClient.setLastName(client.getLastName());
            existingClient.setEmail(client.getEmail());
            existingClient.setTelephone(client.getTelephone());
            existingClient.setAge(client.getAge());
            existingClient.setGender(client.getGender());
            clientService.saveClient(existingClient);
        }

        return "redirect:/trainer/clients";
    }


    /*
    this deletes the selected client
     */
    @GetMapping("/trainer/clients/delete/{idClient}")
    public String deleteClient(@PathVariable("idClient") long id) {
        clientService.deleteClient(id);
        return "redirect:/trainer/clients";
    }


    /*
    this opens the page to assign/create a training program to a specific client
     */
    @GetMapping("/trainer/trainingplan/new/{idClient}")
    public String createTrainingPlan(@PathVariable("idClient") Long idClient, Model model) {
        model.addAttribute("idClient", idClient);
        return "createTrainingPlan";
    }

    /*
    this opens the page to create a training program
     */
    @GetMapping("/trainer/trainingplan/new")
    public String createTrainingPlanM() {

        return "createTrainingPlan";
    }

    /*
    this saves the training program to the client and the database
     */
    @PostMapping("/trainer/trainingplan/save")
    public String saveTrainingPlan(@ModelAttribute TrainingPlanForm trainingPlanForm,
                                   @RequestParam("idClient") Long idClient,
                                   RedirectAttributes redirectAttributes) {

        TrainingPlan trainingPlan = new TrainingPlan();
        List<Exercise> exercises = new ArrayList<>();

        // Temporarily save the training plan to generate an ID
        TrainingPlan savedTrainingPlan = trainingPlanService.saveTrainingPlan(idClient, exercises);

        for (ExerciseForm exerciseForm : trainingPlanForm.getExercises()) {
            Exercise exercise = new Exercise();
            // Set exercise properties
            exercise.setName(exerciseForm.getName());
            exercise.setSets(exerciseForm.getSets());
            exercise.setRepetitions(exerciseForm.getReps());
            exercise.setDuration(exerciseForm.getDuration());
            exercise.setNotes(exerciseForm.getNotes());

            // Image handling
            MultipartFile imageFile = exerciseForm.getImageFile();
            if (imageFile != null && !imageFile.isEmpty()) {
                try {
                    String imageUrl = imageStorageService.saveImage(imageFile);
                    exercise.setImageUrl(imageUrl);
                } catch (Exception e) {
                    // Handle the exception
                    System.out.println("Error adding the image " + e);
                }
            }

            exercise.setTrainingPlan(savedTrainingPlan); // Set the saved training plan
            exerciseService.saveExercise(exercise);
            exercises.add(exercise);
        }

        // Update the training plan with the exercises
        savedTrainingPlan.setExercises(exercises);
        trainingPlanService.updateTrainingPlan(savedTrainingPlan); // Update the training plan

        redirectAttributes.addFlashAttribute("message", "Training plan saved successfully");
        return "redirect:/trainer/clients/trainingPlan/view/" + idClient;
    }

    /*
    View a client's current training program. If no program is assigned to the client it redirects to the client list
     */
    @GetMapping("/trainer/clients/trainingPlan/view/{idClient}")
    public String viewTrainingPlan(@PathVariable("idClient") Long idClient, Model model) {

        Client client = clientService.getClientById(idClient);
        TrainingPlan trainingPlan = client.getTrainingProgram();

        List<Exercise> exercises = trainingPlanService.getAllExercise(idClient);
        if (exercises.isEmpty() || trainingPlan == null) {
            // Handle the case where the client does not have an assigned training plan or no exercises
            return "redirect:/trainer/clients";
        }


        model.addAttribute("exercises", exercises);
        model.addAttribute("client", client);
        return "viewTrainingPlan"; // Name of your Thymeleaf template
    }





}
