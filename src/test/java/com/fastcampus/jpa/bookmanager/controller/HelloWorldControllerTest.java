package com.fastcampus.jpa.bookmanager.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest // mock mvc를 사용할 수 있게 됨
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc; // mock mvc를 사용할 수 있게 됨

    @Test
    void helloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello-world")) // get 요청을 보내는 것
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("hello-world"));
    }


}