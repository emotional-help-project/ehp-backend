package com.epam.rd.service.impl;

import com.epam.rd.exceptions.BadRequest;
import com.epam.rd.model.entity.BaseEntity;
import com.epam.rd.repository.BaseRepository;
import com.epam.rd.service.CommonService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseServiceImpl<ENTITY extends BaseEntity, L extends Number> implements CommonService<ENTITY, Long> {
    private final BaseRepository<ENTITY> baseRepository;

    @Override
    public List<ENTITY> getAll() {
        return baseRepository.findAll();
    }

    @Override
    public Optional<ENTITY> getById(Long ID) {
        return baseRepository.findById(ID);
    }

    @Override
    public ENTITY create(ENTITY entity) {
        if (entity.getId() == null) {
            baseRepository.save(entity);
        }
        else throw new BadRequest("ID must be bull");
        return entity;
    }

    @Override
    public ENTITY update(ENTITY entity) {
        if (entity.getId() != null) {
            baseRepository.save(entity);
        }
        else throw new BadRequest("ID must not be bull");
        return entity;
    }

    @Override
    public void deleteById(Long ID) {
        baseRepository.deleteById(ID);
    }

    @Override
    public boolean delete(Long ID) {
        baseRepository.deleteById(ID);
        return true;
    }
}
