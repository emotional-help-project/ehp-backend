package com.epam.rd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AnswerProcessingException extends RuntimeException {

    public AnswerProcessingException(String errorMessage) {
        super(errorMessage);
    }
}
