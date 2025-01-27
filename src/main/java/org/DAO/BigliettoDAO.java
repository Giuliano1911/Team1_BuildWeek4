package org.DAO;

import jakarta.persistence.EntityManager;
import org.trasporti.Abbonamento;
import org.trasporti.Biglietto;

import java.util.List;

public class BigliettoDAO {
    private EntityManager em;

    public BigliettoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Biglietto biglietto) {
        this.em.getTransaction().begin();
        this.em.persist(biglietto);
        this.em.getTransaction().commit();
    }

    public Biglietto getById(Long id) {
        this.em.getTransaction().begin();
        Biglietto bigliettoDaTrovare = this.em.find(Biglietto.class, id);
        this.em.getTransaction().commit();
        return bigliettoDaTrovare;
    }

    public void deleteById(Long id) {
        this.em.getTransaction().begin();
        Biglietto bigliettoDaEliminare = this.em.find(Biglietto.class, id);
        if (bigliettoDaEliminare != null) {
            this.em.remove(bigliettoDaEliminare);
            System.out.println("Biglietto Scaduto");
        } else System.out.println("Biglietto non trovato");
        this.em.getTransaction().commit();
    }

    public List<Biglietto> getAll() {
        this.em.getTransaction().begin();
        List<Biglietto> list = em.createQuery("SELECT b FROM Biglietto b", Biglietto.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }
}
