package com.caiobruno.payments.controller;


import com.caiobruno.payments.domain.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentControlerTest {

    private MockMvc mockMvc;

    @Autowired
    private PaymentControler controller;

    Payment payments = new Payment("","","",0.0,"", LocalDate.now(),"" );


    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    @Order(1)
    public  void pagamentoRealizadoTest() throws Exception {
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(payments))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
