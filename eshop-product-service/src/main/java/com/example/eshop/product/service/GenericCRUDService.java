package com.example.eshop.product.service;

import java.util.List;

public interface GenericCRUDService<T, U> {

    T create(T entity);

    T update(T entity);

    void remove(Long id);

    T findById(U id);

    List<T> findAll();

    void removeAll();
}