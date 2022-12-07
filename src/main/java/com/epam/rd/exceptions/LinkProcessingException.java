package com.epam.rd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LinkProcessingException extends RuntimeException {

    public LinkProcessingException(String errorMessage) {
        super(errorMessage);
    }
}
