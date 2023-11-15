package com.example.userservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class Controller {

    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return null;
    }

    @PostMapping("/logout")
    public void logout() {

    }
}
