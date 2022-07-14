package com.example.greetings.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

public class GreetingGenerator {
    private final NamesFactory factory;

    public GreetingGenerator() {
        factory = new NamesFactory();
    }

    String greet(String input) {
        Names normal = factory.normal(input);
        Names shouted = factory.shouted(input);

        if (shouted.length() > 0 && normal.length() > 0) {
            return "Hello, %s. AND HELLO, %s!".formatted(normal.join(), shouted.join());
        }

        if (shouted.length() > 0) {
            return "HELLO, %s!".formatted(shouted.join());
        }

        return "Hello, %s.".formatted(normal.join());
    }

    private abstract static class Names {
        private final String[] names;

        private Names(String[] input) {
            this.names = input;
        }

        public String join() {
            String greeted;
            if (length() > 2) {
                greeted = String.join(", ", exceptLast());
                return "%s, %s %s".formatted(greeted, conjunction(), last());
            }

            return String.join(" %s ".formatted(conjunction()), all());
        }

        abstract protected String conjunction();

        public String last() {
            return names[length() - 1];
        }

        public int length() {
            return names.length;
        }

        public String[] exceptLast() {
            return Arrays.copyOfRange(names, 0, length() - 1);
        }

        public String[] all() {
            return names;
        }
    }

    private class Shouted extends Names {
        private Shouted(String[] input) {
            super(input);
        }

        @Override
        public String conjunction() {
            return "AND";
        }
    }

    private class Normal extends Names {
        private Normal(String[] input) {
            super(input);
        }

        @Override
        public String conjunction() {
            return "and";
        }
    }


    class NamesFactory {
        public Names normal(String input) {
            String[] theNames = filter(input, (name) -> !name.equals(name.toUpperCase()));

            return new Normal(theNames);
        }

        public Names shouted(String input) {
            String[] theNames = filter(input, (name) -> name.equals(name.toUpperCase()));

            return new Shouted(theNames);
        }

        private String[] filter(String input, Function<String, Boolean> selector) {
            String[] names = input.split("\s*,\s*");
            Collection<String> selected = new ArrayList<>();
            for (String name : names) {
                if (selector.apply(name)) {
                    selected.add(name);
                }
            }
            return selected.toArray(new String[0]);
        }
    }

}