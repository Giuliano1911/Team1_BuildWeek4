package org.DAO;

import java.util.List;

public interface DAO<T,E> {

    void save(T o);

    T getById(E id);

    void deleteById(E id);

    List<T> getAll();
}
