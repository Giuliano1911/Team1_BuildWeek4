package org.DAO;

import jakarta.persistence.EntityManager;
import org.trasporti.Tessera;

import java.util.List;

public class TesseraDAO {

    private EntityManager em;

    public TesseraDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tessera tessera) {
        this.em.getTransaction().begin();
        this.em.persist(tessera);
        this.em.getTransaction().commit();
    }

    public Tessera getById(Long id) {
        this.em.getTransaction().begin();
        Tessera tesseraDaTrovare = this.em.find(Tessera.class, id);
        this.em.getTransaction().commit();
        return tesseraDaTrovare;
    }

    public void deleteById(Long id) {
        this.em.getTransaction().begin();
        Tessera tesseraDaEliminare = this.em.find(Tessera.class, id);
        if (tesseraDaEliminare != null) {
            this.em.remove(tesseraDaEliminare);
            System.out.println("Tessera eliminata");
        } else System.out.println("Tessera non trovata");
        this.em.getTransaction().commit();
    }

    public List<Tessera> getAll() {
        this.em.getTransaction().begin();
        List<Tessera> list = em.createQuery("SELECT t FROM Tessera t", Tessera.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }
}

