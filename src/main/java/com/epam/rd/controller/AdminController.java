package com.epam.rd.controller;

import com.epam.rd.payload.request.AdviceAdminRequest;
import com.epam.rd.payload.request.QuestionAnswerAdminRequest;
import com.epam.rd.service.AdminService;
import com.epam.rd.service.AdviceLinkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;
    private AdviceLinkService adviceLinkService;

    @PostMapping("/test")
    public ResponseEntity<?> addQuestionAnswersForTest(@RequestBody QuestionAnswerAdminRequest request) {
        return new ResponseEntity<>(adminService.addQuestionAnswersForTest(request), HttpStatus.CREATED);
    }

    @PostMapping("/advice")
    public ResponseEntity<?> addAdviceWithLinksForTest(@RequestBody AdviceAdminRequest request) {
        return new ResponseEntity<>(adminService.addAdviceWithLinksForTest(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/advice-link/{id}")
    public ResponseEntity<?> deleteAdviceLink(@PathVariable Long id) {
        adviceLinkService.deleteAdviceLink(id);
        return ResponseEntity.noContent().build();
    }


}
