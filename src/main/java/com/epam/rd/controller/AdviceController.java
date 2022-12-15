package com.epam.rd.controller;

import com.epam.rd.service.AdviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advice")
@AllArgsConstructor
public class AdviceController {

    private AdviceService adviceService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdvice(@PathVariable Long id) {
        adviceService.deleteAdvice(id);
        return ResponseEntity.noContent().build();
    }

}
