package com.epam.rd.service.impl;

import com.epam.rd.exceptions.TestProcessingException;
import com.epam.rd.model.dto.AdviceDto;
import com.epam.rd.model.dto.AnswerDto;
import com.epam.rd.model.dto.QuestionDto;
import com.epam.rd.model.entity.Advice;
import com.epam.rd.model.entity.AdviceLink;
import com.epam.rd.model.entity.Question;
import com.epam.rd.model.entity.Test;
import com.epam.rd.model.mapper.AdviceMapper;
import com.epam.rd.model.mapper.AnswerMapper;
import com.epam.rd.model.mapper.QuestionMapper;
import com.epam.rd.payload.request.AdviceAdminRequest;
import com.epam.rd.payload.request.QuestionAnswerAdminRequest;
import com.epam.rd.payload.response.NewQuestionResponse;
import com.epam.rd.repository.*;
import com.epam.rd.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private static final String CANNOT_FIND_TEST = "Cannot find test with ID=";
    private static final String CANNOT_FIND_LINK = "Cannot find link with ID=";

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final TestRepository testRepository;
    private final AdviceRepository adviceRepository;
    private final LinkRepository linkRepository;
    private final AdviceLinkRepository adviceLinkRepository;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final AdviceMapper adviceMapper;

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

    @Transactional
    @Override
    public String addAdviceWithLinksForTest(AdviceAdminRequest request) {
        Test test = testRepository.findById(request.getTestId())
                .orElseThrow(() -> new TestProcessingException(CANNOT_FIND_TEST + request.getTestId()));
        Advice advice = adviceRepository.save(adviceMapper.toEntity(new AdviceDto()
                .setTitle(request.getAdviceTitle())
                .setScoreFrom(request.getScoreFrom())
                .setScoreTo(request.getScoreTo())
                .setTest(test)));

        request.getLinkIds().forEach(linkId -> adviceLinkRepository.save(
                new AdviceLink()
                        .setAdvice(advice)
                        .setLink(linkRepository.findById(linkId)
                                .orElseThrow(() -> new TestProcessingException(CANNOT_FIND_LINK + linkId)))
        ));

        return request.getAdviceTitle();
    }
}
