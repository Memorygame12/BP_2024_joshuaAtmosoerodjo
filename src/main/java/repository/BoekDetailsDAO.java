package repository;

import entity.BoekDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class BoekDetailsDAO {
    private EntityManagerFactory emf;

    public BoekDetailsDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void saveBoekDetails(BoekDetails boekDetails) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(boekDetails);
        em.getTransaction().commit();

    }

    public BoekDetails getBoekDetails(int id) {
        EntityManager em = emf.createEntityManager();
        BoekDetails boekDetails = em.find(BoekDetails.class, id);

        return boekDetails;
    }

    public void updateBoekDetails(BoekDetails boekDetails) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(boekDetails);
        em.getTransaction().commit();

    }

    public void deleteBoekDetails(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        BoekDetails boekDetails = em.find(BoekDetails.class, id);
        if (boekDetails != null) {
            em.remove(boekDetails);
        }
        em.getTransaction().commit();

    }
}
