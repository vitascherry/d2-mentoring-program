package com.example.training.toto.graphy;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.graphy.proxy.Proxy;
import com.example.training.persistence.EntityManagerHelper;
import com.example.training.persistence.guice.PersistenceModule;
import com.example.training.persistence.proxy.EntityManagerInvocationHandler;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.repository.impl.TotoRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TotoModule implements Module {

    @Override
    public void configure(Linker linker) {
        new PersistenceModule().configure(linker);

        linker.install(EntityManagerFactory.class, SingletonFactory.of(this::createEntityManagerFactory));
        linker.install(TotoRepository.class, SingletonFactory.of(this::createTotoRepository));
    }

    protected EntityManagerFactory createEntityManagerFactory(Linker linker) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.example.training.toto");
        linker.bindProvision(EntityManagerFactory.class, entityManagerFactory::close);
        return entityManagerFactory;
    }

    protected TotoRepository createTotoRepository(Linker linker) {
        Factory<EntityManagerHelper> entityManagerHelperFactory = linker.factoryFor(EntityManagerHelper.class);
        EntityManagerHelper entityManagerHelper = entityManagerHelperFactory.get(linker);
        Factory<EntityManager> entityManagerFactory = linker.factoryFor(EntityManager.class);
        EntityManager entityManager = entityManagerFactory.get(linker);

        TotoRepository totoRepository = new TotoRepositoryImpl(entityManager);

        // Using JDK proxy to intercept TotoRepository methods for transaction management
        return Proxy.of(new EntityManagerInvocationHandler<>(entityManagerHelper, totoRepository), TotoRepositoryImpl.class);
    }
}
