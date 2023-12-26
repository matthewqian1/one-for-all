package org.example;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/review")
public class Controller {

    @Autowired
    private ReviewService service;


    @PostMapping("/add")
    public ResponseEntity addReview() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/productReviews/{id}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable String id) {
        return ResponseEntity.ok(service.getProductReviews(id));
    }

    @GetMapping("/productRating/{id}")
    public ResponseEntity<Double> getProductRating(@PathVariable String id) {
        return ResponseEntity.ok(service.getProductRating(id));
    }


}