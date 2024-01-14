package repository;

import entity.Boek;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class BoekDAO {
    private EntityManagerFactory emf;

    public BoekDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void saveBoek(Boek boek) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(boek);
        em.getTransaction().commit();
        em.close();
    }

    public Boek getBoek(int id) {
        EntityManager em = emf.createEntityManager();
        Boek boek = em.find(Boek.class, id);
        em.close();
        return boek;
    }

    public void updateBoek(Boek boek) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(boek);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteBoek(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Boek boek = em.find(Boek.class, id);
        if (boek != null) {
            em.remove(boek);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Boek> getAllBoeken() {
        EntityManager em = emf.createEntityManager();
        List<Boek> boeken = em.createQuery("SELECT b FROM Boek b", Boek.class).getResultList();
        em.close();
        return boeken;
    }
    // In BoekDAO.java
    public boolean isBoekBeschikbaar(int boekId) {
        EntityManager em = emf.createEntityManager();
        long count = (long) em.createQuery("SELECT COUNT(u) FROM Uitlening u WHERE u.boek.id = :boekId AND u.teruggebrachtOp IS NULL")
                .setParameter("boekId", boekId)
                .getSingleResult();
        em.close();
        return count == 0; // Boek is beschikbaar als er geen uitleningen zijn waar het niet is teruggebracht
    }

}
