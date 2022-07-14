package com.example.greetings.controller;

public class GreetingGenerator {
    public GreetingGenerator() {
    }

    String greet(String name) {
        String[] names = name.split("\s*,\s*");
        String greeted = String.join(" and ", names);

        if (greeted.toUpperCase().equals(greeted)) {
            return "HELLO, %s.".formatted(greeted);
        }
        return "Hello, %s.".formatted(greeted);
    }
}