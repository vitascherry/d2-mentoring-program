package com.example.training.persistence;

import com.example.training.persistence.EntityManagerHelper;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class ThreadLocalEntityManager implements EntityManager {

    private final EntityManagerHelper entityManagerHelper;

    @Override
    public void persist(Object o) {
        entityManagerHelper.getEntityManager().persist(o);
    }

    @Override
    public <T> T merge(T t) {
        return entityManagerHelper.getEntityManager().merge(t);
    }

    @Override
    public void remove(Object o) {
        entityManagerHelper.getEntityManager().remove(o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o) {
        return entityManagerHelper.getEntityManager().find(aClass, o);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, Map<String, Object> map) {
        return entityManagerHelper.getEntityManager().find(aClass, o, map);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType) {
        return entityManagerHelper.getEntityManager().find(aClass, o, lockModeType);
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LockModeType lockModeType, Map<String, Object> map) {
        return entityManagerHelper.getEntityManager().find(aClass, o, lockModeType, map);
    }

    @Override
    public <T> T getReference(Class<T> aClass, Object o) {
        return entityManagerHelper.getEntityManager().getReference(aClass, o);
    }

    @Override
    public void flush() {
        entityManagerHelper.getEntityManager().flush();
    }

    @Override
    public void setFlushMode(FlushModeType flushModeType) {
        entityManagerHelper.getEntityManager().setFlushMode(flushModeType);
    }

    @Override
    public FlushModeType getFlushMode() {
        return entityManagerHelper.getEntityManager().getFlushMode();
    }

    @Override
    public void lock(Object o, LockModeType lockModeType) {
        entityManagerHelper.getEntityManager().lock(o, lockModeType);
    }

    @Override
    public void lock(Object o, LockModeType lockModeType, Map<String, Object> map) {
        entityManagerHelper.getEntityManager().lock(o, lockModeType, map);
    }

    @Override
    public void refresh(Object o) {
        entityManagerHelper.getEntityManager().refresh(o);
    }

    @Override
    public void refresh(Object o, Map<String, Object> map) {
        entityManagerHelper.getEntityManager().refresh(o, map);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType) {
        entityManagerHelper.getEntityManager().refresh(o, lockModeType);
    }

    @Override
    public void refresh(Object o, LockModeType lockModeType, Map<String, Object> map) {
        entityManagerHelper.getEntityManager().refresh(o, lockModeType, map);
    }

    @Override
    public void clear() {
        entityManagerHelper.getEntityManager().clear();
    }

    @Override
    public void detach(Object o) {
        entityManagerHelper.getEntityManager().detach(o);
    }

    @Override
    public boolean contains(Object o) {
        return entityManagerHelper.getEntityManager().contains(o);
    }

    @Override
    public LockModeType getLockMode(Object o) {
        return entityManagerHelper.getEntityManager().getLockMode(o);
    }

    @Override
    public void setProperty(String s, Object o) {
        entityManagerHelper.getEntityManager().setProperty(s, o);
    }

    @Override
    public Map<String, Object> getProperties() {
        return entityManagerHelper.getEntityManager().getProperties();
    }

    @Override
    public Query createQuery(String s) {
        return entityManagerHelper.getEntityManager().createQuery(s);
    }

    @Override
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
        return entityManagerHelper.getEntityManager().createQuery(criteriaQuery);
    }

    @Override
    public Query createQuery(CriteriaUpdate criteriaUpdate) {
        return entityManagerHelper.getEntityManager().createQuery(criteriaUpdate);
    }

    @Override
    public Query createQuery(CriteriaDelete criteriaDelete) {
        return entityManagerHelper.getEntityManager().createQuery(criteriaDelete);
    }

    @Override
    public <T> TypedQuery<T> createQuery(String s, Class<T> aClass) {
        return entityManagerHelper.getEntityManager().createQuery(s, aClass);
    }

    @Override
    public Query createNamedQuery(String s) {
        return entityManagerHelper.getEntityManager().createNamedQuery(s);
    }

    @Override
    public <T> TypedQuery<T> createNamedQuery(String s, Class<T> aClass) {
        return entityManagerHelper.getEntityManager().createNamedQuery(s, aClass);
    }

    @Override
    public Query createNativeQuery(String s) {
        return entityManagerHelper.getEntityManager().createNativeQuery(s);
    }

    @Override
    public Query createNativeQuery(String s, Class aClass) {
        return entityManagerHelper.getEntityManager().createNativeQuery(s, aClass);
    }

    @Override
    public Query createNativeQuery(String s, String s1) {
        return entityManagerHelper.getEntityManager().createNativeQuery(s, s1);
    }

    @Override
    public StoredProcedureQuery createNamedStoredProcedureQuery(String s) {
        return entityManagerHelper.getEntityManager().createNamedStoredProcedureQuery(s);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s) {
        return entityManagerHelper.getEntityManager().createStoredProcedureQuery(s);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s, Class... classes) {
        return entityManagerHelper.getEntityManager().createStoredProcedureQuery(s, classes);
    }

    @Override
    public StoredProcedureQuery createStoredProcedureQuery(String s, String... strings) {
        return entityManagerHelper.getEntityManager().createStoredProcedureQuery(s, strings);
    }

    @Override
    public void joinTransaction() {
        entityManagerHelper.getEntityManager().joinTransaction();
    }

    @Override
    public boolean isJoinedToTransaction() {
        return entityManagerHelper.getEntityManager().isJoinedToTransaction();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return entityManagerHelper.getEntityManager().unwrap(aClass);
    }

    @Override
    public Object getDelegate() {
        return entityManagerHelper.getEntityManager().getDelegate();
    }

    @Override
    public void close() {
        entityManagerHelper.getEntityManager().close();
    }

    @Override
    public boolean isOpen() {
        return entityManagerHelper.getEntityManager().isOpen();
    }

    @Override
    public EntityTransaction getTransaction() {
        return entityManagerHelper.getEntityManager().getTransaction();
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerHelper.getEntityManager().getEntityManagerFactory();
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return entityManagerHelper.getEntityManager().getCriteriaBuilder();
    }

    @Override
    public Metamodel getMetamodel() {
        return entityManagerHelper.getEntityManager().getMetamodel();
    }

    @Override
    public <T> EntityGraph<T> createEntityGraph(Class<T> aClass) {
        return entityManagerHelper.getEntityManager().createEntityGraph(aClass);
    }

    @Override
    public EntityGraph<?> createEntityGraph(String s) {
        return entityManagerHelper.getEntityManager().createEntityGraph(s);
    }

    @Override
    public EntityGraph<?> getEntityGraph(String s) {
        return entityManagerHelper.getEntityManager().getEntityGraph(s);
    }

    @Override
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> aClass) {
        return entityManagerHelper.getEntityManager().getEntityGraphs(aClass);
    }
}
