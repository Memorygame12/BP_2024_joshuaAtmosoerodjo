package repository;

import entity.Lid;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class LidDAO {
    private EntityManagerFactory emf;

    public LidDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void saveLid(Lid lid) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(lid);
        em.getTransaction().commit();
        em.close();
    }

    public Lid getLid(int id) {
        EntityManager em = emf.createEntityManager();
        Lid lid = em.find(Lid.class, id);
        em.close();
        return lid;
    }

    public void updateLid(Lid lid) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(lid);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteLid(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Lid lid = em.find(Lid.class, id);
        if (lid != null) {
            em.remove(lid);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Lid> getAllLeden() {
        EntityManager em = emf.createEntityManager();
        List<Lid> leden = em.createQuery("SELECT l FROM Lid l", Lid.class).getResultList();
        em.close();
        return leden;
    }
}
