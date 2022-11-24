package com.epam.rd.service;

import com.epam.rd.model.entity.Type;

import java.util.List;
import java.util.Optional;

public interface CommonService<ENTITY, ID> {
    List<ENTITY> getAll();

    Optional<ENTITY> getById(Long ID);

    ENTITY create(ENTITY entity);

    ENTITY update(ENTITY entity);

    void deleteById(Long ID);

    boolean delete(Long ID);


}
