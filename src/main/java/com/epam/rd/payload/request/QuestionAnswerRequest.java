package com.epam.rd.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class QuestionAnswerRequest {

    private Long questionId;
    private List<Long> answerIds;
}
