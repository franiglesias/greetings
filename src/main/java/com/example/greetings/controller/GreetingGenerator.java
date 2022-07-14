package com.example.greetings.controller;

import java.util.Arrays;

public class GreetingGenerator {
    public GreetingGenerator() {
    }

    String greet(String input) {
        Names allNames = new Names(input);

        String greeted = allNames.join();

        if (greeted.toUpperCase().equals(greeted)) {
            return "HELLO, %s!".formatted(greeted);
        }
        return "Hello, %s.".formatted(greeted);
    }

    private class Names {
        private final String[] names;
        public Names(String input) {
            this.names = input.split("\s*,\s*");
        }

        public String join() {
            String greeted;
            if (length() > 2) {
                greeted = String.join(", ", exceptLast());
                return greeted + ", and " + last();
            }

            return String.join(" and ", all());
        }
        public String last() {
            return names[length() - 1];
        }

        private int length() {
            return names.length;
        }

        public String[] exceptLast() {
            return Arrays.copyOfRange(names, 0, length() - 1);
        }

        public String[] all() {
            return names;
        }
    }
}