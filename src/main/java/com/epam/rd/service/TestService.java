package com.epam.rd.service;

import com.epam.rd.model.entity.Answer;
import com.epam.rd.model.entity.Question;
import com.epam.rd.payload.response.FinalizeTestResponse;
import com.epam.rd.payload.request.UserAnswersRequest;
import com.epam.rd.model.entity.Test;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TestService extends CommonService<Test, Long> {

    Long startTest(Long userId, Long testId);

    void submitUserAnswers(UserAnswersRequest userAnswersDto, Long sessionId);

    /**
     * @param testId Test ID
     * @param skip   Number of questions to be skipped when the test is loaded
     * @param take   Number of questions to be loaded
     * @return Test
     */
    Page<Question> getTestQuestionsPaginated(Long testId, int skip, int take);

    FinalizeTestResponse finalizeTest(Long sessionId);

    long calculateUserScore(List<Answer> answers);
}
