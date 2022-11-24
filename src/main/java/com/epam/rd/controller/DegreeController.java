package com.epam.rd.controller;

import com.epam.rd.exceptions.ApiException;
import com.epam.rd.model.entity.Degree;
import com.epam.rd.service.DegreeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/degrees")
public class DegreeController {
    private final DegreeService degreeService;
    Logger logger = LoggerFactory.getLogger(DegreeController.class.getName());


    @GetMapping()
    public ResponseEntity<List<Degree>> getAll() {
        return ResponseEntity.ok(degreeService.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(degreeService.getById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Degree degree) {
        try {
            return new ResponseEntity<>(degreeService.create(degree), HttpStatus.CREATED);
        } catch (Exception x) {
            return ResponseEntity.badRequest().body(new ApiException(HttpStatus.BAD_REQUEST, x.getMessage(), ""));
        }

    }

    @PutMapping
    public ResponseEntity<Degree> update(@RequestBody Degree degree) {
        return new ResponseEntity<>(degreeService.update(degree), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Degree> delete(@PathVariable Long id) {
        if (degreeService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
