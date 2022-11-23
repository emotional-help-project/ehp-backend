package com.epam.rd.service;

import com.epam.rd.model.entity.Type;

import java.util.List;
import java.util.Optional;

public interface CommonService<T,ID> {
    List<T> getAll();
   Optional<T> getById(Long ID);
    T create(T entity);
    T update(T entity);
    void deleteById(Long ID);
    boolean delete(Long ID);


}
