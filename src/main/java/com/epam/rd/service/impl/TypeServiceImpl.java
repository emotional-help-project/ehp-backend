package com.epam.rd.service.impl;

import com.epam.rd.model.entity.Type;
import com.epam.rd.repository.BaseRepository;
import org.springframework.stereotype.Service;


@Service
public class TypeServiceImpl extends BaseServiceImpl<Type, Long> {

    public TypeServiceImpl(BaseRepository<Type> baseRepository) {
        super(baseRepository);
    }
}
