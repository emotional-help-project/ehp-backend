package com.epam.rd.service.impl;

import com.epam.rd.model.entity.Test;
import com.epam.rd.repository.BaseRepository;

import org.springframework.stereotype.Service;



@Service
public class TestServiceImpl extends BaseServiceImpl<Test, Long> {

    public TestServiceImpl(BaseRepository<Test> baseRepository) {
        super(baseRepository);
    }
}
