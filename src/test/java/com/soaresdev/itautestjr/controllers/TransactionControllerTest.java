package com.soaresdev.itautestjr.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soaresdev.itautestjr.dtos.TransactionDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL_PATH = "/transaction";
    private static final String INVALID_TRANSACTION_JSON = "{\"amount\": \"sadsad\", \"dataHour\": \"dasdsd\"}";
    private static TransactionDto validTransactionDtoRequest;
    private static TransactionDto invalidTransactionDtoRequest;

    @BeforeAll
    static void init() {
        validTransactionDtoRequest = new TransactionDto(BigDecimal.ONE, OffsetDateTime.now());
        invalidTransactionDtoRequest = new TransactionDto(BigDecimal.valueOf(-1), OffsetDateTime.now().plusHours(4));
    }

    @Test
    void shouldCreateTransactionAndReturn201() throws Exception {
        mvc.perform(post(URL_PATH).contentType(MediaType.APPLICATION_JSON_VALUE).
                content(objectMapper.writeValueAsString(validTransactionDtoRequest))).
                andExpect(status().isCreated()).andExpect(content().string("")).
                andExpect(header().exists("Location")).
                andExpect(header().string("Location", containsString("/statistic"))).
                andDo(print());
    }

    @Test
    void shouldReturn400WhenInvalidTransactionJsonInCreateTransaction() throws Exception {
        mvc.perform(post(URL_PATH).contentType(MediaType.APPLICATION_JSON_VALUE).
                content(INVALID_TRANSACTION_JSON)).andExpect(status().isBadRequest()).
                andExpect(content().string("")).andDo(print());
    }

    @Test
    void shouldReturn422WhenInvalidTransactionInCreateTransaction() throws Exception {
        mvc.perform(post(URL_PATH).contentType(MediaType.APPLICATION_JSON_VALUE).
                content(objectMapper.writeValueAsString(invalidTransactionDtoRequest))).
                andExpect(status().isUnprocessableEntity()).andExpect(content().string("")).
                andDo(print());
    }

    @Test
    void shouldDeleteAllTransactionsAndReturn200() throws Exception {
        mvc.perform(delete(URL_PATH)).andExpect(status().isOk()).
                andExpect(content().string("")).andDo(print());
    }
}