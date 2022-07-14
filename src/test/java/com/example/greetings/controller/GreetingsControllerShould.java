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
        MvcResult result = givenIDontProvideAName();
        String contents = whenTheSystemGreetsMe(result);
        thenTheGreetingIs(contents, "Hello, World.");
    }

    @Test
    void greetAPerson() throws Exception {
        MvcResult result = givenIProvideAName("Fran");
        String contents = whenTheSystemGreetsMe(result);
        thenTheGreetingIs(contents, "Hello, Fran.");
    }

    @Test
    void greetShouting() throws Exception {
        MvcResult result = givenIProvideAName("FRAN");
        String contents = whenTheSystemGreetsMe(result);
        thenTheGreetingIs(contents, "HELLO, FRAN.");
    }

    private void thenTheGreetingIs(String contents, String expected) {
        assertThat(contents).isEqualTo(expected);
    }

    private String whenTheSystemGreetsMe(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

    private MvcResult givenIDontProvideAName() throws Exception {
        MvcResult result = mockMvc.perform(get("/hello"))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        return result;
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
