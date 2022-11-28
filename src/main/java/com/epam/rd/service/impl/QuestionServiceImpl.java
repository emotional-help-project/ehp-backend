package com.epam.rd.service.impl;

import com.epam.rd.exceptions.BadRequest;
import com.epam.rd.model.entity.Question;
import com.epam.rd.repository.BaseRepository;
import com.epam.rd.repository.QuestionRepository;
import com.epam.rd.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question,Long> {

    public QuestionServiceImpl(BaseRepository<Question> baseRepository) {
        super(baseRepository);
    }
}
