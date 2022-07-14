package com.example.greetings.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GreetingsController {
    @GetMapping("/hello")
    public ResponseEntity<String> greetTheWorld() {
        return new ResponseEntity<>("Hello, World.", HttpStatus.OK);
    }

    @GetMapping("/hello/{name}")
    public ResponseEntity<String> greetAPerson(@PathVariable String name) {
        return new ResponseEntity<>(greet(name), HttpStatus.OK);
    }

    private String greet(String name) {
        if (name.toUpperCase().equals(name)) {
            return "HELLO, %s.".formatted(name);
        }
        return "Hello, %s.".formatted(name);
    }
}
