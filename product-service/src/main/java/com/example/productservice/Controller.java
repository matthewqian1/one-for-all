package com.example.productservice;

import com.google.gson.Gson;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ProblemDetail;
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

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        System.out.println(id);
        return ResponseEntity.ok(service.getProduct(id));
    }

    @GetMapping("/byCategory/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam Category category) {
        return ResponseEntity.ok(service.getProductsByCategory(category));
    }

    @GetMapping("/getImages")
    public ResponseEntity<List<String>> getProductImages(@RequestParam String[] ids) {
        return ResponseEntity.ok(service.getImages(ids));
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<String>> getCategories() {
        return ResponseEntity.ok(service.getCategories());
    }


}
