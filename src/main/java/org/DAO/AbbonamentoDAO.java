package org.DAO;

import jakarta.persistence.EntityManager;
import org.trasporti.Abbonamento;


import java.util.List;

public class AbbonamentoDAO {
    private EntityManager em;

    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Abbonamento abbonamento) {
        this.em.getTransaction().begin();
        this.em.persist(abbonamento);
        this.em.getTransaction().commit();
    }

    public Abbonamento getById(Long id) {
        this.em.getTransaction().begin();
        Abbonamento abbonamentoDaTrovare = this.em.find(Abbonamento.class, id);
        this.em.getTransaction().commit();
        return abbonamentoDaTrovare;
    }

    public void deleteById(Long id) {
        this.em.getTransaction().begin();
        Abbonamento abbonamentoDaEliminare = this.em.find(Abbonamento.class, id);
        if (abbonamentoDaEliminare != null) {
            this.em.remove(abbonamentoDaEliminare);
            System.out.println("Abbonamento Scaduto");
        } else System.out.println("Abbonamento non trovato");
        this.em.getTransaction().commit();
    }

    public List<Abbonamento> getAll() {
        this.em.getTransaction().begin();
        List<Abbonamento> list = em.createQuery("SELECT a FROM Abbonamento a", Abbonamento.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }
}
