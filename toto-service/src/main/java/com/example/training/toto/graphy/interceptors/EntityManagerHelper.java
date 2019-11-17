package com.example.training.toto.graphy.interceptors;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@AllArgsConstructor
public class EntityManagerHelper {

    private final EntityManagerFactory entityManagerFactory;
    private final ThreadLocal<EntityManager> threadLocal;

    public EntityManager getEntityManager() {
        EntityManager entityManager = threadLocal.get();

        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
            threadLocal.set(entityManager);
        }
        return entityManager;
    }

    public void closeEntityManager() {
        EntityManager entityManager = threadLocal.get();
        if (entityManager != null) {
            entityManager.close();
            threadLocal.set(null);
        }
    }

    public void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }

    public void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public void rollbackTransaction() {
        getEntityManager().getTransaction().rollback();
    }

    public void commitTransaction() {
        getEntityManager().getTransaction().commit();
    }
}
