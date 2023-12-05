package com.example.tpathletics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularRouteController {

    /*
    THIS CONTROLLER IS USED TO COMMUNICATE THE SPRING PROJECT TO THE ANGULAR PROJECT
     */
    /*
    opens up the main store page from angular
     */
    @RequestMapping( value="/store")
    public String redirect() {
        // Forward to home page so that route is preserved.
        return "forward:/store/index.html";
    }

    /*
    gets all the products in the database
     */
    @RequestMapping(value="/store/products")
    public String productRoute(){
        return "forward:/store/index.html";
    }

    /*
    gets all the products in the cart
     */
    @RequestMapping(value="/store/cart")
    public String cartRoute(){
        return "forward:/store/index.html";
    }
}