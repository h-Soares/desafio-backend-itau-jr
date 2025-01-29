package com.soaresdev.itautestjr.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StatisticControllerTest {
    @Autowired
    private MockMvc mvc;

    private static final String URL_PATH = "/statistic";

    @Test
    void shouldCalculateStatisticsAndReturn200() throws Exception {
        mvc.perform(get(URL_PATH)).andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$.count").value(Matchers.isA(Integer.class))).
                andExpect(jsonPath("$.sum").value(Matchers.isA(Double.class))).
                andExpect(jsonPath("$.avg").value(Matchers.isA(Double.class))).
                andExpect(jsonPath("$.min").value(Matchers.isA(Double.class))).
                andExpect(jsonPath("$.max").value(Matchers.isA(Double.class))).
                andDo(print());
    }

    @Test
    void shouldReturn422WhenNegativeSecondsInCalculateStatistics() throws Exception {
        mvc.perform(get(URL_PATH).queryParam("seconds", "-1")).
                andExpect(status().isUnprocessableEntity()).andExpect(content().string("")).
                andDo(print());
    }

    @Test
    void shouldReturn422WhenInvalidSecondsInCalculateStatistics() throws Exception {
        mvc.perform(get(URL_PATH).queryParam("seconds", "dksaodkasodkas")).
                andExpect(status().isUnprocessableEntity()).andExpect(content().string("")).
                andDo(print());
    }
}