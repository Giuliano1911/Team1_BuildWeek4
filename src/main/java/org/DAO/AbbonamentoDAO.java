package org.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.trasporti.Abbonamento;


import java.time.LocalDate;
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

    public List<Abbonamento> getByDistributore(Long id) {
        this.em.getTransaction().begin();
        TypedQuery<Abbonamento> query = em.createQuery("SELECT a FROM Abbonamento a WHERE a.distributore.id = :id", Abbonamento.class);
        List<Abbonamento> list = query.setParameter("id", id).getResultList();
        this.em.getTransaction().commit();
        return list;
    }

    public Integer getByData(LocalDate dataInizio, LocalDate dataFine) {
        this.em.getTransaction().begin();
        List<Abbonamento> listaAbbonamenti = getAll();
        int result = 0;
        for (Abbonamento a : listaAbbonamenti) {
            if (a.getDataEmissione().isAfter(dataInizio) && a.getDataEmissione().isBefore(dataFine))
                result++;
        }
        this.em.getTransaction().commit();
        return result;
    }
}
