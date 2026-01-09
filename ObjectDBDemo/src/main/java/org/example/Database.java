package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Database implements AutoCloseable {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ObjectDBDemo.odb"); // Create the EntityManagerFactory
    private EntityManager em;

    public Database() { // Constructor
        try{
            em = emf.createEntityManager(); // Create the EntityManager
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void insert(Object object) { // Insert a new object in the database
        try {
            em.getTransaction().begin();

            if (object instanceof ItemSales) {
                ItemSales is = (ItemSales) object;
                if (is.getItem() != null) {
                    Item managedItem = em.find(Item.class, is.getItem().getId());
                    if (managedItem != null) is.setItem(managedItem);
                }
                if (is.getSales() != null) {
                    Sales managedSales = em.find(Sales.class, is.getSales().getId());
                    if (managedSales != null) is.setSales(managedSales);
                }
            }
            em.persist(object); // Persist the object
            em.getTransaction().commit(); // Commit the transaction
        } catch (Exception e) {
            this.rollBack(); // Rollback the transaction if an error occurs
            System.err.println(e.getMessage());
        }
    }

    public void delete(Object object) { // Delete an object from the database
        try {
            em.getTransaction().begin();
            // Ensure the entity is managed before removal to avoid
            Object managed = em.contains(object) ? object : em.merge(object);
            em.remove(managed); // Remove the managed entity
            em.getTransaction().commit();
        } catch (Exception e) {
            this.rollBack();
            System.err.println(e.getMessage());
        }
    }

    public void update(Object object) { // Update an object in the database
        try {
            em.getTransaction().begin();
            em.merge(object); // Merge the object
            em.getTransaction().commit();
        } catch (Exception e) {
            this.rollBack();
            System.err.println(e.getMessage());
        }
    }

    public List<?> selectAll(Class<?> typeClass) { // Select all objects of a given type
        try {
            return switch (typeClass.getSimpleName()) { // Switch case to select the correct query
                case ("Customer") -> em.createQuery("SELECT c FROM Customer c").getResultList();
                case ("Item") -> em.createQuery("SELECT i FROM Item i").getResultList();
                case ("Sales") -> em.createQuery("SELECT s FROM Sales s").getResultList();
                case ("ItemSales") -> em.createQuery("SELECT itsa FROM ItemSales itsa").getResultList();
                default -> null;
            };
        } catch (Exception e) {
            this.rollBack();
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void close() { // Close the EntityManager
        em.close();
    }

    public void rollBack() { // Rollback the transaction
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

}
