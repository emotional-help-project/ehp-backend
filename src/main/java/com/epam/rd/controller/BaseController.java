package com.epam.rd.controller;

import com.epam.rd.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
public class BaseController<ENTITY> {
    private final CommonService<ENTITY, Long> commonService;
    Logger logger = LoggerFactory.getLogger(BaseController.class.getName());


    @GetMapping()
    public ResponseEntity<List<ENTITY>> getAll() {
        return ResponseEntity.ok(commonService.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(commonService.getById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }


}