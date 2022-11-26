package com.epam.rd.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class UserAnswersRequest {

    private Long userId;
    private Long testId;
    private List<QuestionAnswerRequest> questionAnswerRequests;
}
