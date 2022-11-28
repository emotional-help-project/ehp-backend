package com.epam.rd.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AnswerResponse {

    private Long answerId;
    private String answerText;
    private boolean isChecked;

}
