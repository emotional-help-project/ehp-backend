package com.epam.rd.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class QuestionAnswerAdminRequest {

    private String questionTitle;
    private boolean allowsMultipleAnswers;
    private int questionNumber;
    private Long testId;
    private List<NewAnswer> answers;
}
