package com.epam.rd.service.impl;

import com.epam.rd.model.entity.Answer;

import com.epam.rd.repository.BaseRepository;

import org.springframework.stereotype.Service;


@Service
public class AnswerServiceImpl extends BaseServiceImpl<Answer, Long> {

    public AnswerServiceImpl(BaseRepository<Answer> baseRepository) {
        super(baseRepository);
    }
}
