package com.epam.rd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AdviceProcessingException extends RuntimeException {

    public AdviceProcessingException(String errorMessage) {
        super(errorMessage);
    }
}
