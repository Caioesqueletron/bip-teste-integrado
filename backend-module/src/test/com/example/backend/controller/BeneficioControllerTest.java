package com.example.backend.controller;

import com.example.backend.models.Beneficio;
import com.example.backend.models.request.BeneficioRequest;
import com.example.backend.service.BeneficioEjbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeneficioController.class)
class BeneficioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeneficioEjbService service;

    @Test
    void shouldListBeneficios() throws Exception {
        when(service.findAll()).thenReturn(List.of(new Beneficio()));

        mockMvc.perform(get("/api/v1/beneficios"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateBeneficio() throws Exception {
        BeneficioRequest req = new BeneficioRequest();
        req.setNome("VA");
        req.setValor(BigDecimal.valueOf(100));

        when(service.create(any())).thenReturn(new Beneficio());

        mockMvc.perform(post("/api/v1/beneficios")
                        .contentType("application/json")
                        .content("""
                                {
                                  "nome": "VA",
                                  "valor": 100
                                }
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldTransfer() throws Exception {
        mockMvc.perform(post("/api/v1/beneficios/transfer")
                        .contentType("application/json")
                        .content("""
                                {
                                  "fromId": 1,
                                  "toId": 2,
                                  "amount": 50
                                }
                                """))
                .andExpect(status().isNoContent());
    }
}
