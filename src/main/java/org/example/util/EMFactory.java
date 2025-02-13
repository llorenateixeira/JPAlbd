package org.example.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMFactory {

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("lab05_jpa");


    public EntityManager getEntityManager() {
        return factory.createEntityManager();

    }

    public void close() {
        factory.close();
    }
}