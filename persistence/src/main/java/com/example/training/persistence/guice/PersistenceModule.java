package com.example.training.persistence.guice;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.persistence.EntityManagerHelper;
import com.example.training.persistence.ThreadLocalEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceModule implements Module {

    @Override
    public void configure(Linker linker) {
        linker.install(EntityManagerFactory.class, SingletonFactory.of(this::createEntityManagerFactory));
        linker.install(EntityManagerHelper.class, SingletonFactory.of(this::createEntityManagerHelper));
        linker.install(EntityManager.class, SingletonFactory.of(this::createEntityManager));
    }

    protected EntityManagerFactory createEntityManagerFactory(Linker linker) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        linker.bindProvision(EntityManagerFactory.class, entityManagerFactory::close);
        return entityManagerFactory;
    }

    protected EntityManagerHelper createEntityManagerHelper(Linker linker) {
        Factory<EntityManagerFactory> entityManagerFactoryFactory = linker.factoryFor(EntityManagerFactory.class);
        EntityManagerFactory entityManagerFactory = entityManagerFactoryFactory.get(linker);

        return new EntityManagerHelper(entityManagerFactory, new ThreadLocal<>());
    }

    protected EntityManager createEntityManager(Linker linker) {
        Factory<EntityManagerHelper> entityManagerHelperFactory = linker.factoryFor(EntityManagerHelper.class);
        EntityManagerHelper entityManagerHelper = entityManagerHelperFactory.get(linker);

        return new ThreadLocalEntityManager(entityManagerHelper);
    }
}
