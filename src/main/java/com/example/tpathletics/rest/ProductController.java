package com.example.tpathletics.rest;

import com.example.tpathletics.entity.Product;
import com.example.tpathletics.service.ImageStorageService;
import com.example.tpathletics.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    /*
    RestAPI to communicate the information from the database to the Angular application to generate the list of all the products
     */

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.findAllProducts(); // Method to retrieve products from the database
        return ResponseEntity.ok(products);
    }




}
