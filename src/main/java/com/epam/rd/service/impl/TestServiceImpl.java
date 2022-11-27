package com.epam.rd.service.impl;

import com.epam.rd.exceptions.*;
import com.epam.rd.model.dto.TestResultDto;
import com.epam.rd.model.dto.UserAnswersDto;
import com.epam.rd.model.entity.*;
import com.epam.rd.model.mapper.TestResultMapper;
import com.epam.rd.payload.request.QuestionAnswerUserRequest;
import com.epam.rd.payload.response.AnswerResponse;
import com.epam.rd.payload.response.FinalizeTestResponse;
import com.epam.rd.payload.request.UserAnswersRequest;
import com.epam.rd.model.mapper.UserAnswersMapper;
import com.epam.rd.payload.response.QuestionAnswersResponse;
import com.epam.rd.payload.response.TestQuestionsResponse;
import com.epam.rd.repository.*;

import com.epam.rd.service.TestService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class TestServiceImpl extends BaseServiceImpl<Test, Long> implements TestService {
    private static final String CANNOT_FIND_USER = "Cannot find user with the ID=";
    private static final String CANNOT_FIND_TEST = "Cannot find test with ID=";
    private static final String CANNOT_FIND_SESSION = "Cannot find session with ID=";
    private static final String CANNOT_FIND_QUESTION = "Cannot find question with ID=";
    private static final String CANNOT_FIND_ANSWER = "Cannot find answer with ID=";
    private static final String CANNOT_FIND_ADVICE_FOR_USER_SCORE = "Cannot find advice for user's score=";
    private static final String ONLY_ONE_ANSWER_FOR_QUESTION = "Only one answer can be submitted for question with ID=";
    private final TestRepository testRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final UserAnswersRepository userAnswersRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AdviceRepository adviceRepository;
    private final LinkRepository linkRepository;
    private final TestResultRepository testResultRepository;
    private final UserAnswersMapper userAnswersMapper;
    private final TestResultMapper testResultMapper;

    public TestServiceImpl(BaseRepository<Test> baseRepository, TestRepository testRepository,
                           SessionRepository sessionRepository, UserRepository userRepository,
                           UserAnswersRepository userAnswersRepository, QuestionRepository questionRepository,
                           AnswerRepository answerRepository, AdviceRepository adviceRepository,
                           LinkRepository linkRepository, TestResultRepository testResultRepository,
                           UserAnswersMapper userAnswersMapper, TestResultMapper testResultMapper) {
        super(baseRepository);
        this.testRepository = testRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.userAnswersRepository = userAnswersRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.adviceRepository = adviceRepository;
        this.linkRepository = linkRepository;
        this.testResultRepository = testResultRepository;
        this.userAnswersMapper = userAnswersMapper;
        this.testResultMapper = testResultMapper;
    }

    @Transactional
    @Override
    public TestQuestionsResponse getTestQuestionsPaginated(Long testId, Long sessionId, int skip, int take) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new TestProcessingException(CANNOT_FIND_TEST + testId));
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionProcessingException(CANNOT_FIND_SESSION + sessionId));
        List<UserAnswersDto> userAnswersDtoList = userAnswersRepository.findUserAnswersBySession(session)
                .stream().map(userAnswersMapper::toDto).toList();

        Pageable pageable = createPageRequest(skip, take);
        List<Question> questionsPaginated = questionRepository.findTestQuestionsPaginated(test, pageable).getContent();

        List<QuestionAnswersResponse> items = new ArrayList<>();
        questionsPaginated.forEach(q -> items.add(new QuestionAnswersResponse()
                .setQuestionId(q.getId())
                .setQuestionText(q.getTitle())
                .setAnswers(answerRepository.findByQuestion(q).stream()
                        .map(a -> new AnswerResponse().setAnswerId(a.getId())
                                .setAnswerText(a.getTitle())
                                .setChecked(userAnswersDtoList.stream()
                                        .map(UserAnswersDto::getQuestion)
                                        .anyMatch(uaq -> uaq.equals(a.getQuestion()))))
                        .toList())));

        Long totalNumberOfTestQuestions = questionRepository.countByTest(test);
        return new TestQuestionsResponse().setTotalNumberOfTestQuestions(totalNumberOfTestQuestions)
                .setItems(items);
    }

    @Transactional
    @Override
    public Long startTest(Long userId, Long testId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserProcessingException(CANNOT_FIND_USER + userId));
        Test test = testRepository.findById(testId).orElseThrow(() -> new TestProcessingException(CANNOT_FIND_TEST + testId));
        Session newTestSession = new Session()
                .setUser(user)
                .setTest(test);
        Session session = sessionRepository.save(newTestSession);
        return session.getId();
    }

    @Transactional
    @Override
    public void submitUserAnswers(UserAnswersRequest userAnswersRequest, Long sessionId) {

        List<Long> questionIdsFromUserAnswersRequest = userAnswersRequest.getQuestionAnswerUserRequests()
                .stream().map(QuestionAnswerUserRequest::getQuestionId).toList();
        List<Question> questionsFromRequestWithOnePossibleAnswer =
                questionRepository.findByQuestionIdsAndMultipleAnswers(questionIdsFromUserAnswersRequest, false);

        userAnswersRequest.getQuestionAnswerUserRequests().forEach(qau -> {
            if (qau.getAnswerIds().size() > 1 &&
                    questionsFromRequestWithOnePossibleAnswer.stream().map(Question::getId)
                            .anyMatch(id -> Objects.equals(id, qau.getQuestionId()))) {
                throw new TestProcessingException(ONLY_ONE_ANSWER_FOR_QUESTION + qau.getQuestionId());
            }
        });

        User user = userRepository.findById(userAnswersRequest.getUserId())
                .orElseThrow(() -> new UserProcessingException(CANNOT_FIND_USER + userAnswersRequest.getUserId()));
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionProcessingException(CANNOT_FIND_SESSION + sessionId));

        List<UserAnswersDto> userAnswersDtoListToBeSaved = new ArrayList<>();

        userAnswersRequest.getQuestionAnswerUserRequests()
                .forEach(qa -> qa.getAnswerIds()
                        .forEach(answerId -> userAnswersDtoListToBeSaved.add(
                                new UserAnswersDto()
                                        .setUser(user)
                                        .setQuestion(questionRepository.findById(qa.getQuestionId())
                                                .orElseThrow(() -> new QuestionProcessingException(CANNOT_FIND_QUESTION + qa.getQuestionId())))
                                        .setAnswer(answerRepository.findById(answerId)
                                                .orElseThrow(() -> new AnswerProcessingException(CANNOT_FIND_ANSWER + answerId)))
                                        .setSession(session)
                        )));

        userAnswersDtoListToBeSaved.forEach(userAnswersDto -> userAnswersRepository.save(
                userAnswersMapper.toEntity(userAnswersDto)
        ));
    }

    @Transactional
    @Override
    public FinalizeTestResponse finalizeTest(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionProcessingException(CANNOT_FIND_SESSION + sessionId));
        List<Answer> userFinalAnswersForTest = getUsersAllAnswersForTestBySession(session);

        Long testId = session.getTest().getId();
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new TestProcessingException(CANNOT_FIND_TEST + testId));
        List<Question> allQuestionsOfTest = questionRepository.findAllByTest(test);

        for (Question question : allQuestionsOfTest) {
            if(userFinalAnswersForTest.stream().map(Answer::getQuestion)
                    .noneMatch(userQuestion -> userQuestion.equals(question))) {
                return null;
            }
        }

        long userScore = calculateUserScore(userFinalAnswersForTest);
        Advice advice = adviceRepository.findAdviceByUserScore(userScore)
                .orElseThrow(() -> new AdviceProcessingException(CANNOT_FIND_ADVICE_FOR_USER_SCORE + userScore));
        List<Link> usefulLinks = linkRepository.getLinksForAdvice(advice);

        TestResultDto testResult = new TestResultDto()
                .setResult(userScore)
                .setAdvice(advice)
                .setUser(session.getUser())
                .setSession(session);
        testResultRepository.save(testResultMapper.toEntity(testResult));

        return new FinalizeTestResponse()
                .setAdviceDescription(advice.getTitle())
                .setScoreFrom(advice.getScoreFrom())
                .setScoreTo(advice.getScoreTo())
                .setUserScore(userScore)
                .setLinks(usefulLinks);
    }

    private List<Answer> getUsersAllAnswersForTestBySession(Session session) {

        List<UserAnswersDto> userAnswersDtoList =
                userAnswersRepository.findUserAnswersBySession(session)
                        .stream().map(userAnswersMapper::toDto).toList();
        return userAnswersDtoList.stream()
                .map(UserAnswersDto::getAnswer).toList();
    }

    @Transactional
    @Override
    public long calculateUserScore(List<Answer> answers) {
        return answers.stream()
                .mapToLong(Answer::getScore)
                .sum();
    }

    private PageRequest createPageRequest(int skip, int take) {
        return PageRequest.of(skip, take, Sort.by("number").ascending());
    }

}
