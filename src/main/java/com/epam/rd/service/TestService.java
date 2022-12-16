package com.epam.rd.service;

import com.epam.rd.payload.response.*;
import com.epam.rd.payload.request.UserAnswersRequest;
import com.epam.rd.model.entity.Test;

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

    Long calculateResult(Long sessionId);

    FinalizeTestResponse finalizeTest(Long sessionId, Long userScore);

    TestPageForUserResponse getAllTestsForUser(Long userId, int skip, int take);

    EmotionMapResponse getEmotionMapByTest(Long userId, Long testId);

    UserEmotionStatisticsResponse getUserEmotionStatistics(Long id);

    List<TestShortDetailsResponse> getTestsFinishedByUser(Long userId);
}
