package com.example.tpathletics.controller;

import com.example.tpathletics.entity.Client;
import com.example.tpathletics.entity.Trainer;
import com.example.tpathletics.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    /*
    This controller applies to the main page of the webapp and links various html pages together
     */

    @Autowired
    private LoginService loginService;

    @GetMapping("")
    public String mainPage(){
        return "mainpage";
    }

    @GetMapping("pricing")
    public String pricing(){
        return "Pricing";
    }
    @GetMapping("login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("book")
    public String book(){
        return "book";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        Trainer trainer = loginService.authenticateTrainer(email, password);
        if (trainer != null) {
            return "redirect:/trainer";
        }

        return "login";
    }



}
