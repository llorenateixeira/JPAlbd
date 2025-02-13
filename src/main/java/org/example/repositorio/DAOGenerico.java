package org.example.repositorio;

import jakarta.persistence.*;
import java.util.Objects;

public class DAOGenerico<T> {

    private final EntityManager manager;
    private final Class<T> clazz;

    public DAOGenerico(EntityManager manager, Class<T> clazz) {
        this.manager = manager;
        this.clazz = clazz;
    }

    public T buscaPorId(Integer id) {
        return manager.find(clazz, id);
    }

    public T salvaOuAtualiza(T t) {
        if (!manager.contains(t) && Objects.isNull(manager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(t))) {
            manager.persist(t);
            return t;
        } else {
            return manager.merge(t);
        }
    }

    public void remove(T t) {
        manager.remove(manager.contains(t) ? t : manager.merge(t));
    }

    public void commit() {
        manager.getTransaction().commit();
    }

    public void begin() {
        manager.getTransaction().begin();
    }

    public void rollback() {
        manager.getTransaction().rollback();
    }
}
