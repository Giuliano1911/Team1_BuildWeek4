package org.DAO;

import jakarta.persistence.EntityManager;
import org.trasporti.Tessera;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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

    public boolean verificaValidita(Long id) {
        this.em.getTransaction().begin();
        Tessera tessera = this.em.find(Tessera.class, id);
        this.em.getTransaction().commit();
        if (tessera == null) {
            System.out.println("Tessera non trovata");
            return false;
        }
        if (tessera.getDataEmissione().plusDays(360).isAfter(LocalDate.now())) {
            System.out.println(tessera);
            return true;
        }
        System.out.println("Tessera scaduta, rinnovo in corso...");
        tessera.setDataEmissione(LocalDate.now());
        System.out.println("Scheda rinnovata, nuova scadenza: " + tessera.getDataEmissione().plusDays(360));
        return true;
    }
}

