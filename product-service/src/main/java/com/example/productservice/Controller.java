package com.example.productservice;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class Controller {

    @Autowired
    ProductService service;


    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product) {
        service.addProduct(product);
    }

    @GetMapping("/byCategory/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam Category category) {
        return ResponseEntity.ok(service.getProductsByCategory(category));
    }


}
