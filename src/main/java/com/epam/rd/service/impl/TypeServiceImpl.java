package com.epam.rd.service.impl;

import com.epam.rd.model.entity.TestType;
import com.epam.rd.repository.BaseRepository;
import org.springframework.stereotype.Service;


@Service
public class TypeServiceImpl extends BaseServiceImpl<TestType, Long> {

    public TypeServiceImpl(BaseRepository<TestType> baseRepository) {
        super(baseRepository);
    }
}
