package org.DAO;

import jakarta.persistence.EntityManager;
import org.trasporti.Distributore;


import java.util.List;

public class DistributoreDAO {
    private EntityManager em;

    public DistributoreDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Distributore distributore) {
        this.em.getTransaction().begin();
        this.em.persist(distributore);
        this.em.getTransaction().commit();
    }

    public Distributore getById(Long id) {
        this.em.getTransaction().begin();
        Distributore distributoreDaTrovare = this.em.find(Distributore.class, id);
        this.em.getTransaction().commit();
        return distributoreDaTrovare;
    }

    public void deleteById(Long id) {
        this.em.getTransaction().begin();
        Distributore distributoreDaEliminare = this.em.find(Distributore.class, id);
        if (distributoreDaEliminare != null) {
            this.em.remove(distributoreDaEliminare);
            System.out.println("Distributore eliminato");
        } else System.out.println("Distributore non trovato");
        this.em.getTransaction().commit();
    }

    public List<Distributore> getAll() {
        this.em.getTransaction().begin();
        List<Distributore> list = em.createQuery("SELECT d FROM Distributore d", Distributore.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }

    public List<Distributore> getAllActive() {
        this.em.getTransaction().begin();
        List<Distributore> list = em.createQuery("SELECT d FROM Distributore d WHERE d.disponibilita = ATTIVO", Distributore.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }
}