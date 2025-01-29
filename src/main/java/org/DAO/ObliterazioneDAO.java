package org.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.trasporti.Obliterazione;

import java.time.LocalDate;
import java.util.List;

public class ObliterazioneDAO {

    private EntityManager em;

    public ObliterazioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Obliterazione obliterazione) {
        this.em.getTransaction().begin();
        this.em.persist(obliterazione);
        this.em.getTransaction().commit();
    }

    public Obliterazione getById(Long id) {
        this.em.getTransaction().begin();
        Obliterazione obliterazioneDaTrovare = this.em.find(Obliterazione.class, id);
        this.em.getTransaction().commit();
        return obliterazioneDaTrovare;
    }

    public void deleteById(Long id) {
        this.em.getTransaction().begin();
        Obliterazione obliterazioneDaEliminare = this.em.find(Obliterazione.class, id);
        if (obliterazioneDaEliminare != null) {
            this.em.remove(obliterazioneDaEliminare);
            System.out.println("Obliterazione eliminata");
        } else System.out.println("Obliterazione non trovata");
        this.em.getTransaction().commit();
    }

    public List<Obliterazione> getAll() {
        this.em.getTransaction().begin();
        List<Obliterazione> list = em.createQuery("SELECT o FROM Obliterazione o", Obliterazione.class).getResultList();
        this.em.getTransaction().commit();
        return list;
    }

    public Integer getByMezzo(Long id) {
        this.em.getTransaction().begin();
        TypedQuery<Obliterazione> query = em.createQuery("SELECT o FROM Obliterazione o WHERE o.mezzo.id = :id", Obliterazione.class);
        int nBiglietti = query.setParameter("id", id).getResultList().size();
        this.em.getTransaction().commit();
        return nBiglietti;
    }

    public Integer getByData(LocalDate dataInizio, LocalDate dataFine) {
        this.em.getTransaction().begin();
        List<Obliterazione> listaObliterazioni = getAll();
        int result = 0;
        for (Obliterazione o : listaObliterazioni) {
            if (o.getBiglietto().getDataEmissione().isAfter(dataInizio) && o.getBiglietto().getDataEmissione().isBefore(dataFine))
                result++;
        }
        this.em.getTransaction().commit();
        return result;
    }
}
