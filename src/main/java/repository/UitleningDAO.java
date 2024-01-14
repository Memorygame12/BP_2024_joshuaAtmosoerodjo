package repository;

import entity.Uitlening;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class UitleningDAO {
    private EntityManagerFactory emf;

    public UitleningDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void saveUitlening(Uitlening uitlening) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(uitlening);
        em.getTransaction().commit();
        em.close();
    }

    public Uitlening getUitlening(int id) {
        EntityManager em = emf.createEntityManager();
        Uitlening uitlening = em.find(Uitlening.class, id);
        em.close();
        return uitlening;
    }

    public void updateUitlening(Uitlening uitlening) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(uitlening);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteUitlening(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Uitlening uitlening = em.find(Uitlening.class, id);
        if (uitlening != null) {
            em.remove(uitlening);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Uitlening> getAllUitleningen() {
        EntityManager em = emf.createEntityManager();
        List<Uitlening> uitleningen = em.createQuery("SELECT u FROM Uitlening u", Uitlening.class).getResultList();
        em.close();
        return uitleningen;
    }
}
