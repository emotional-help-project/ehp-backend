package com.epam.rd.controller;

import com.epam.rd.payload.request.UpdateAdviceRequest;
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

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateAdvice(@RequestBody UpdateAdviceRequest updateAdviceRequest) {

        return ResponseEntity.ok(adviceService.updateAdvice(updateAdviceRequest));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(adviceService.getAllAdvice());
    }

    @GetMapping("/testId")
    public ResponseEntity<?> getAllAdviceByTestId(@RequestParam Long testId) {
        return ResponseEntity.ok(adviceService.getAllAdviceByTestId(testId));
    }
}
