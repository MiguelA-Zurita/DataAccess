package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Database implements AutoCloseable {
    private EntityManager em;

    public Database() {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdbdemo.odb");) {
            em = emf.createEntityManager();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void insert(Object object) {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            this.rollBack();
            System.err.println(e.getMessage());
        }
    }

    public void delete(Object object) {
        try {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            this.rollBack();
            System.err.println(e.getMessage());
        }
    }

    public void update(Object object) {
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            this.rollBack();
            System.err.println(e.getMessage());
        }
    }

    public List<?> selectAll(Class<?> typeClass) {
        try {
            return em.createQuery("select * FROM " + typeClass.getSimpleName()).getResultList();
        } catch (Exception e) {
            this.rollBack();
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void close() {
        em.close();
    }

    public void rollBack() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

}
