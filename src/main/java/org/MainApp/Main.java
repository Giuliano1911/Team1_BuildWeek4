package org.MainApp;

import jakarta.persistence.EntityManager;
import org.DAO.AbbonamentoDAO;
import org.DAO.BigliettoDAO;
import org.DAO.DistributoreDAO;
import org.DAO.MezzoDAO;
import org.trasporti.ENUMS.StatoMezzo;
import org.trasporti.ENUMS.TipoMezzo;
import org.trasporti.EntityManagerUtil;
import org.trasporti.Mezzo;

public class Main {
    public static void main(String[] args) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            MezzoDAO mezzoDAO = new MezzoDAO(em);
            DistributoreDAO distributoreDAO = new DistributoreDAO(em);
            AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
            BigliettoDAO bigliettoDAO = new BigliettoDAO(em);

        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}