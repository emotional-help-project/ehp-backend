package com.epam.rd.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class TestQuestionsResponse {

    private long totalNumberOfTestQuestions;
    private List<QuestionAnswersResponse> items;
}
