package com.example.backend.service;

import com.example.backend.models.Beneficio;
import com.example.backend.models.request.BeneficioRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class BeneficioEjbService {

    @PersistenceContext
    private EntityManager em;

    public List<Beneficio> findAll() {
        return em.createQuery(
                "select b from Beneficio b", Beneficio.class
        ).getResultList();
    }

    public Beneficio findById(Long id) {
        Beneficio beneficio = em.find(Beneficio.class, id);
        if (beneficio == null) {
            throw new IllegalStateException("Benefício não encontrado");
        }
        return beneficio;
    }

    public Beneficio create(BeneficioRequest beneficioRequest) {
        if (beneficioRequest.getValor() == null || beneficioRequest.getValor().signum() < 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        Beneficio beneficio = new Beneficio();
        beneficio.setNome(beneficioRequest.getNome());
        beneficio.setDescricao(beneficioRequest.getDescricao());
        beneficio.setValor(beneficioRequest.getValor());

        em.persist(beneficio);
        return beneficio;
    }

    public Beneficio update(Long id, Beneficio beneficio) {
        Beneficio existing = findById(id);
        existing.setNome(beneficio.getNome());
        existing.setValor(beneficio.getValor());
        return existing; // managed
    }

    public void delete(Long id) {
        Beneficio beneficio = findById(id);
        em.remove(beneficio);
    }


    public void transfer(Long fromId, Long toId, BigDecimal amount) {

        if (fromId == null || toId == null) {
            throw new IllegalArgumentException("Ids inválidos");
        }

        if (fromId.equals(toId)) {
            throw new IllegalArgumentException("Transferência inválida");
        }

        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }

        try {
            Beneficio from = findById(fromId);
            Beneficio to   = findById(toId);

            if (from.getValor().compareTo(amount) < 0) {
                throw new IllegalStateException("Saldo insuficiente");
            }

            from.setValor(from.getValor().subtract(amount));
            to.setValor(to.getValor().add(amount));
            em.persist(from);
            em.persist(from);
            // dirty checking + optimistic locking (@Version)
            // conflito => OptimisticLockException => rollback

        } catch (OptimisticLockException e) {
            throw new IllegalStateException(
                    "Conflito de concorrência, tente novamente", e
            );
        }
    }
}
