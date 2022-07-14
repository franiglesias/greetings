package com.example.greetings.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = GreetingsController.class)
@ExtendWith(SpringExtension.class)
public class GreetingsControllerShould {
    private Application application;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        application = new Application(this.mockMvc);
    }

    @Test
    void greetTheWorld() throws Exception {
        givenNamesThenGreetingIs("", "Hello, World.");
    }

    @Test
    void greetAPerson() throws Exception {
        givenNamesThenGreetingIs("Fran", "Hello, Fran.");
    }

    @Test
    void greetShouting() throws Exception {
        givenNamesThenGreetingIs("FRAN", "HELLO, FRAN!");
    }

    @Test
    void greetTwoPeople() throws Exception {
        givenNamesThenGreetingIs("Annie, Bart", "Hello, Annie and Bart.");
    }

    @Test
    void greetSeveralPeople() throws Exception {
        givenNamesThenGreetingIs("Anne,Bart,Charles,Monique", "Hello, Anne, Bart, Charles, and Monique.");
    }

    @Test
    void greetMixedPeople() throws Exception {
        givenNamesThenGreetingIs("Anne,BART,Charles,MONIQUE", "Hello, Anne and Charles. AND HELLO, BART AND MONIQUE!");
    }

    private void givenNamesThenGreetingIs(String input, String greeting) throws Exception {
        MvcResult result = givenIProvideAName(input);
        String contents = whenTheSystemGreetsMe(result);
        thenTheGreetingIs(contents, greeting);
    }

    private void thenTheGreetingIs(String contents, String expected) {
        assertThat(contents).isEqualTo(expected);
    }

    private String whenTheSystemGreetsMe(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    private MvcResult givenIProvideAName(String name) throws Exception {

        return application.getHello(name);
    }

    public static class Application {
        private final MockMvc mockMvc;

        public Application(MockMvc mockMvc) {

            this.mockMvc = mockMvc;
        }

        private MvcResult getHello(String name) throws Exception {
            MvcResult result = mockMvc.perform(get("/hello/" + name))
                    .andReturn();

            assertThat(result.getResponse().getStatus()).isEqualTo(200);
            return result;
        }
    }
}
