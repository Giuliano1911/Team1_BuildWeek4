package org.DAO;

import jakarta.persistence.EntityManager;

import org.trasporti.Tratta;

import java.util.List;

public class TrattaDAO {
    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta tratta) {
        this.em.getTransaction().begin();
        this.em.persist(tratta);
        this.em.getTransaction().commit();
    }

    public Tratta getById(Long id) {
        this.em.getTransaction().begin();
        Tratta trattaDaTrovare = this.em.find(Tratta.class, id);
        this.em.getTransaction().commit();
        return trattaDaTrovare;
    }

    public void deleteById(Long id) {
        this.em.getTransaction().begin();
        Tratta mezzoDaEliminare = this.em.find(Tratta.class, id);
        if (mezzoDaEliminare != null) {
            this.em.remove(mezzoDaEliminare);
            System.out.println("Tratta eliminata");
        } else System.out.println("Tratta non trovata");
        this.em.getTransaction().commit();
    }

    public List<Tratta> getAll() {
        this.em.getTransaction().begin();
        List<Tratta> list = em.createQuery("SELECT t FROM Tratta t", Tratta.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }
}

