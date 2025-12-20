package com.example.backend.controller;

import com.example.backend.models.Beneficio;
import com.example.backend.models.request.BeneficioRequest;
import com.example.backend.service.BeneficioEjbService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/beneficios")
public class BeneficioController {

    private final BeneficioEjbService service;

    public BeneficioController(BeneficioEjbService service) {
        this.service = service;
    }

    @GetMapping
    public List<Beneficio> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Beneficio get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Beneficio create(@RequestBody BeneficioRequest beneficio) {
        return service.create(beneficio);
    }

    @PutMapping("/{id}")
    public Beneficio update(
            @PathVariable Long id,
            @RequestBody Beneficio beneficio) {
        return service.update(id, beneficio);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer(
            @RequestParam Long fromId,
            @RequestParam Long toId,
            @RequestParam BigDecimal amount) {
        service.transfer(fromId, toId, amount);
    }
}
