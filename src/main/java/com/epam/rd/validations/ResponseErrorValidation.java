package com.epam.rd.validations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseErrorValidation {

    @Transactional
    public ResponseEntity<Object> mapValidationService(BindingResult result) {
        if (!result.hasErrors()) return null;

        Map<String, String> errorMap = new HashMap<>();

        if (!CollectionUtils.isEmpty(result.getAllErrors())) {
            result.getAllErrors().forEach(error -> {
                errorMap.put(error.getCode(), error.getDefaultMessage());
            });
        }

        result.getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
