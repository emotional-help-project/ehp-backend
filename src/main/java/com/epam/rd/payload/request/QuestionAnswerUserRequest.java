package com.epam.rd.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class QuestionAnswerUserRequest {

    private Long questionId;
    private List<Long> answerIds;
}
