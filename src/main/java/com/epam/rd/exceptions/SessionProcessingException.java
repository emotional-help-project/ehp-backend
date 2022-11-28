package com.epam.rd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SessionProcessingException extends RuntimeException {

    public SessionProcessingException(String errorMessage) {
        super(errorMessage);
    }
}
