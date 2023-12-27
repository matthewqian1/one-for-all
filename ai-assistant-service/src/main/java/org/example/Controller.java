package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/ai")
public class Controller {

    @Autowired
    ApiService service;

    @PostMapping
    public ResponseEntity<String> query(@RequestBody UserQuery query) {
        return ResponseEntity.ok(service.generateResponse(query));
    }
}