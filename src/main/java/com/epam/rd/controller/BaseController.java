package com.epam.rd.controller;

import com.epam.rd.exceptions.ApiException;
import com.epam.rd.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class BaseController <ENTITY> {
    private final CommonService<ENTITY,Long> commonService;
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

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody ENTITY entity) {
        try {
            return new ResponseEntity<>(commonService.create(entity), HttpStatus.CREATED);
        } catch (Exception x) {
            return ResponseEntity.badRequest().body(new ApiException(HttpStatus.BAD_REQUEST, x.getMessage(), ""));
        }

    }

    @PutMapping
    public ResponseEntity<ENTITY> update(@RequestBody ENTITY entity) {
        return new ResponseEntity<>(commonService.update(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ENTITY> delete(@PathVariable Long id) {
        if (commonService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
