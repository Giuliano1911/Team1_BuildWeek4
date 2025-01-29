package org.DAO;

import jakarta.persistence.EntityManager;
import org.trasporti.Distributore;
import org.trasporti.Mezzo;

import java.util.List;

public class MezzoDAO {

    private EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Mezzo mezzo) {
        this.em.getTransaction().begin();
        this.em.persist(mezzo);
        this.em.getTransaction().commit();
    }

    public Mezzo getById(Long id) {
        this.em.getTransaction().begin();
        Mezzo mezzoDaTrovare = this.em.find(Mezzo.class, id);
        this.em.getTransaction().commit();
        return mezzoDaTrovare;
    }

    public void deleteById(Long id) {
        this.em.getTransaction().begin();
        Mezzo mezzoDaEliminare = this.em.find(Mezzo.class, id);
        if (mezzoDaEliminare != null) {
            this.em.remove(mezzoDaEliminare);
            System.out.println("Mezzo eliminato");
        } else System.out.println("Mezzo non trovato");
        this.em.getTransaction().commit();
    }

    public List<Mezzo> getAll() {
        this.em.getTransaction().begin();
        List<Mezzo> list = em.createQuery("SELECT m FROM Mezzo m", Mezzo.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }

    public List<Mezzo> getAllWorking() {
        this.em.getTransaction().begin();
        List<Mezzo> list = em.createQuery("SELECT m FROM Mezzo m WHERE m.statoMezzo = FUNZIONANTE", Mezzo.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }
}
