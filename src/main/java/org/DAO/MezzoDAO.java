package org.DAO;

import jakarta.persistence.EntityManager;
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
        return null;
    }

    public void deleteById(Long id) {

    }

    public List<Mezzo> getAll() {
        return List.of();
    }
}
