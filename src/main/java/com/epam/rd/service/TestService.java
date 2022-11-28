package com.epam.rd.service;

import com.epam.rd.model.dto.AnswerDto;
import com.epam.rd.model.entity.Answer;
import com.epam.rd.payload.response.FinalizeTestResponse;
import com.epam.rd.payload.request.UserAnswersRequest;
import com.epam.rd.model.entity.Test;
import com.epam.rd.payload.response.TestPageForUserResponse;
import com.epam.rd.payload.response.TestQuestionsResponse;

import java.util.List;

public interface TestService extends CommonService<Test, Long> {

    Long startTest(Long userId, Long testId);

    void submitUserAnswers(UserAnswersRequest userAnswersDto, Long sessionId);

    /**
     * @param testId    Test ID
     * @param sessionId Session ID
     * @param skip      Number of questions to be skipped when the test is loaded
     * @param take      Number of questions to be loaded
     * @return Test
     */
    TestQuestionsResponse getTestQuestionsPaginated(Long testId, Long sessionId, int skip, int take);

    FinalizeTestResponse finalizeTest(Long sessionId);

    long calculateUserScore(List<AnswerDto> answers);

    TestPageForUserResponse getAllTestsForUser(Long userId, int skip, int take);

}
