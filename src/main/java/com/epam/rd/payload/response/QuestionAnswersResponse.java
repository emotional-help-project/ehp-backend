package com.epam.rd.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class QuestionAnswersResponse {

    private Long questionId;
    private String questionText;
    private List<AnswerResponse> answers;

}
