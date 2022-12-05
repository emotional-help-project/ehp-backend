package com.epam.rd.controller;

import com.epam.rd.payload.request.QuestionAnswerAdminRequest;
import com.epam.rd.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

    @PostMapping("/test")
    public ResponseEntity<?> addQuestionAnswersForTest(@RequestBody QuestionAnswerAdminRequest request) {
        return new ResponseEntity<>(adminService.addQuestionAnswersForTest(request), HttpStatus.CREATED);
    }


}
