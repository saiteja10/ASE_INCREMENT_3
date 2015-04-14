package com.ase.dao;

import java.io.Serializable;

public interface IGenericDAO<E, K extends Serializable> {
    E findByID(K primaryKey);

    K save(E newInstance);

    void update(E transientObject);

    void saveOrUpdate(E transientObject);

    void delete(E persistentObject);
}
