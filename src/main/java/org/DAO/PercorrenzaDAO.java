package org.DAO;

import jakarta.persistence.EntityManager;
import org.trasporti.ENUMS.StatoMezzo;
import org.trasporti.Percorrenza;

import java.util.List;
import java.util.Objects;

public class PercorrenzaDAO {

    private EntityManager em;

    public PercorrenzaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Percorrenza percorrenza) {
        this.em.getTransaction().begin();
        this.em.persist(percorrenza);
        this.em.getTransaction().commit();
    }

    public Percorrenza getById(Long id) {
        this.em.getTransaction().begin();
        Percorrenza percorrenzaDaTrovare = this.em.find(Percorrenza.class, id);
        this.em.getTransaction().commit();
        return percorrenzaDaTrovare;
    }

    public void deleteById(Long id) {
        this.em.getTransaction().begin();
        Percorrenza percorrenzaDaEliminare = this.em.find(Percorrenza.class, id);
        if (percorrenzaDaEliminare != null) {
            this.em.remove(percorrenzaDaEliminare);
            System.out.println("Percorrenza eliminata");
        } else System.out.println("Percorrenza non trovata");
        this.em.getTransaction().commit();
    }

    public List<Percorrenza> getAll() {
        this.em.getTransaction().begin();
        List<Percorrenza> list = em.createQuery("SELECT o FROM Percorrenza o", Percorrenza.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }

    public List<Percorrenza> getAllWorking() {
        this.em.getTransaction().begin();
        List<Percorrenza> list = em.createQuery("SELECT o FROM Percorrenza o", Percorrenza.class).getResultList()
                .stream().filter(percorrenza -> percorrenza.getMezzo().getStatoMezzo().equals(StatoMezzo.FUNZIONANTE))
                .toList();
        this.em.getTransaction().commit();
        return list;
    }

    public List<Percorrenza> getByMezzoAndTrattaId(Long idMezzo, Long idTratta) {
        this.em.getTransaction().begin();
        List<Percorrenza> list = em.createQuery("SELECT o FROM Percorrenza o", Percorrenza.class).getResultList()
                .stream()
                .filter(percorrenza -> Objects.equals(percorrenza.getMezzo().getId(), idMezzo) && Objects.equals(percorrenza.getTratta().getId(), idTratta))
                .toList();
        this.em.getTransaction().commit();
        return list;
    }
}