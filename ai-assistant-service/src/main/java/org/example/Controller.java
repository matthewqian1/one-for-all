package org.example;

import com.google.gson.Gson;
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
        System.out.println("received " + new Gson().toJson(query));
        return ResponseEntity.ok(service.generateResponse(query));
    }
}