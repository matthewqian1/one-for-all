package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/review")
public class Controller {


    @GetMapping("/add")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(null);
    }
}