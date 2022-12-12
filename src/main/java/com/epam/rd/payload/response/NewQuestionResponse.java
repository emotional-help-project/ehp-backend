package com.epam.rd.payload.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NewQuestionResponse {

    private int questionNumber;
    private String questionTitle;
}
