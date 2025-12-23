package com.example.backend.service;

import com.example.backend.models.Beneficio;
import com.example.backend.models.request.BeneficioRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BeneficioEjbServiceTest {

    @InjectMocks
    private BeneficioEjbService service;

    @Mock
    private EntityManager em;

    @Test
    void shouldCreateBeneficio() {
        BeneficioRequest req = new BeneficioRequest();
        req.setNome("VA");
        req.setDescricao("Desc");
        req.setValor(BigDecimal.valueOf(100));

        Beneficio result = service.create(req);

        verify(em).persist(any(Beneficio.class));
        assertEquals("VA", result.getNome());
        assertEquals(BigDecimal.valueOf(100), result.getValor());
    }

    @Test
    void shouldFailCreateWithNegativeValue() {
        BeneficioRequest req = new BeneficioRequest();
        req.setValor(BigDecimal.valueOf(-10));

        assertThrows(IllegalArgumentException.class,
                () -> service.create(req));
    }

    @Test
    void shouldTransferSuccessfully() {
        Beneficio from = new Beneficio();
        from.setValor(BigDecimal.valueOf(200));

        Beneficio to = new Beneficio();
        to.setValor(BigDecimal.valueOf(50));

        when(em.find(Beneficio.class, 1L)).thenReturn(from);
        when(em.find(Beneficio.class, 2L)).thenReturn(to);

        service.transfer(1L, 2L, BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(100), from.getValor());
        assertEquals(BigDecimal.valueOf(150), to.getValor());
    }

    @Test
    void shouldFailTransferInsufficientBalance() {
        Beneficio from = new Beneficio();
        from.setValor(BigDecimal.valueOf(50));

        when(em.find(Beneficio.class, 1L)).thenReturn(from);
        when(em.find(Beneficio.class, 2L)).thenReturn(new Beneficio());

        assertThrows(IllegalStateException.class,
                () -> service.transfer(1L, 2L, BigDecimal.valueOf(100)));
    }
}