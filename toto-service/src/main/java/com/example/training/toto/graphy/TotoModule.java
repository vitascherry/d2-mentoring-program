package com.example.training.toto.graphy;

import com.example.training.graphy.factory.Factory;
import com.example.training.graphy.factory.SingletonFactory;
import com.example.training.graphy.linker.Linker;
import com.example.training.graphy.module.Module;
import com.example.training.graphy.proxy.Proxy;
import com.example.training.toto.graphy.interceptors.EntityManagerHelper;
import com.example.training.toto.graphy.interceptors.EntityManagerInvocationHandler;
import com.example.training.toto.repository.TotoRepository;
import com.example.training.toto.repository.impl.TotoRepositoryImpl;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TotoModule implements Module {

    @Override
    public void configure(Linker linker) {
        linker.install(EntityManagerFactory.class, SingletonFactory.of(this::createEntityManagerFactory));
        linker.install(EntityManagerHelper.class, SingletonFactory.of(this::createEntityManagerHelper));
        linker.bindProvision(EntityManagerFactory.class, EntityManagerFactory::close);
        linker.install(TotoRepository.class, SingletonFactory.of(this::createTotoRepository));
    }

    protected EntityManagerFactory createEntityManagerFactory(Linker linker) {
        return Persistence.createEntityManagerFactory("com.example.training.toto");
    }

    protected EntityManagerHelper createEntityManagerHelper(Linker linker) {
        Factory<EntityManagerFactory> entityManagerFactoryFactory = linker.factoryFor(EntityManagerFactory.class);
        EntityManagerFactory entityManagerFactory = entityManagerFactoryFactory.get(linker);

        return new EntityManagerHelper(entityManagerFactory, new ThreadLocal<>());
    }

    protected TotoRepository createTotoRepository(Linker linker) {
        Factory<EntityManagerHelper> entityManagerHelperFactory = linker.factoryFor(EntityManagerHelper.class);
        EntityManagerHelper entityManagerHelper = entityManagerHelperFactory.get(linker);
        Provider<EntityManager> entityManagerProvider = entityManagerHelper::getEntityManager;
        TotoRepository totoRepository = new TotoRepositoryImpl(entityManagerProvider);

        // Using JDK proxy to intercept TotoRepository methods for transaction management
        return Proxy.of(new EntityManagerInvocationHandler<>(entityManagerHelper, totoRepository), TotoRepositoryImpl.class);
    }
}
