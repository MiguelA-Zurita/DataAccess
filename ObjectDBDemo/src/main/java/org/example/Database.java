package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Database {
    final EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdbdemo.odb");
    final EntityManager em = emf.createEntityManager();

    public void init(){
        try {
            em.getTransaction().begin();

        }catch (Exception e){
        }
    }

}
