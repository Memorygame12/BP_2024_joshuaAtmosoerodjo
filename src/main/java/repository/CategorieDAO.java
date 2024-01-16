package repository;

import entity.Categorie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CategorieDAO {
    private EntityManagerFactory emf;

    public CategorieDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void saveCategorie(Categorie categorie) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(categorie);
        em.getTransaction().commit();

    }

    public Categorie getCategorie(int id) {
        EntityManager em = emf.createEntityManager();
        Categorie categorie = em.find(Categorie.class, id);

        return categorie;
    }

    public void updateCategorie(Categorie categorie) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(categorie);
        em.getTransaction().commit();

    }

    public void deleteCategorie(int id
    ) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Categorie categorie = em.find(Categorie.class, id);
        if (categorie != null) {
            em.remove(categorie);
        }
        em.getTransaction().commit();

    }

    public List<Categorie> getAllCategorieen() {
        EntityManager em = emf.createEntityManager();
        List<Categorie> categorieen = em.createQuery("SELECT c FROM Categorie c", Categorie.class).getResultList();

        return categorieen;
    }


}