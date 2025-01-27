package org.DAO;

import jakarta.persistence.EntityManager;
import org.trasporti.Manutenzione;

import java.util.List;

public class ManutenzioneDAO {

    private EntityManager em;

    public ManutenzioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Manutenzione manutenzione) {
        this.em.getTransaction().begin();
        this.em.persist(manutenzione);
        this.em.getTransaction().commit();
    }

    public Manutenzione getById(Long id) {
        this.em.getTransaction().begin();
        Manutenzione manutenzioneDaTrovare = this.em.find(Manutenzione.class, id);
        this.em.getTransaction().commit();
        return manutenzioneDaTrovare;
    }

    public void deleteById(Long id) {
        this.em.getTransaction().begin();
        Manutenzione manutenzioneDaEliminare = this.em.find(Manutenzione.class, id);
        if (manutenzioneDaEliminare != null) {
            this.em.remove(manutenzioneDaEliminare);
            System.out.println("Manutenzione eliminata");
        } else System.out.println("Manutenzione non trovata");
        this.em.getTransaction().commit();
    }

    public List<Manutenzione> getAll() {
        this.em.getTransaction().begin();
        List<Manutenzione> list = em.createQuery("SELECT m FROM Manutenzione m", Manutenzione.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }
}
