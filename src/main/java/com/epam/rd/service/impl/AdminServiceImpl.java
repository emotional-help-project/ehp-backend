package com.epam.rd.service.impl;

import com.epam.rd.exceptions.TestProcessingException;
import com.epam.rd.model.dto.AnswerDto;
import com.epam.rd.model.dto.QuestionDto;
import com.epam.rd.model.entity.Question;
import com.epam.rd.model.entity.Test;
import com.epam.rd.model.mapper.AnswerMapper;
import com.epam.rd.model.mapper.QuestionMapper;
import com.epam.rd.payload.request.QuestionAnswerAdminRequest;
import com.epam.rd.payload.response.NewQuestionResponse;
import com.epam.rd.repository.AnswerRepository;
import com.epam.rd.repository.QuestionRepository;
import com.epam.rd.repository.TestRepository;
import com.epam.rd.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private static final String CANNOT_FIND_TEST = "Cannot find test with ID=";

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final TestRepository testRepository;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Transactional
    @Override
    public NewQuestionResponse addQuestionAnswersForTest(QuestionAnswerAdminRequest request) {
        Test test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new TestProcessingException(CANNOT_FIND_TEST + request.getTestId()));
        Question question = questionRepository.save(questionMapper.toEntity(
                new QuestionDto()
                        .setTitle(request.getQuestionTitle())
                        .setMultipleAnswers(request.isAllowsMultipleAnswers())
                        .setNumber(request.getQuestionNumber())
                        .setTest(test)
        ));

        request.getAnswers().forEach(a ->
                answerRepository.save(answerMapper.toEntity(new AnswerDto()
                        .setTitle(a.getTitle())
                        .setScore(a.getScore())
                        .setQuestion(question)))
        );

        return new NewQuestionResponse()
                .setQuestionNumber(question.getNumber())
                .setQuestionTitle(question.getTitle());
    }
}
