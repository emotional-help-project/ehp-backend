package com.epam.rd.controller;

import com.epam.rd.exceptions.AdviceProcessingException;
import com.epam.rd.exceptions.TestProcessingException;
import com.epam.rd.payload.request.UserAnswersRequest;
import com.epam.rd.model.entity.Test;
import com.epam.rd.payload.response.FinalizeTestResponse;
import com.epam.rd.service.CommonService;
import com.epam.rd.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tests")
public class TestController extends BaseController<Test> {

    private final TestService testService;

    public TestController(CommonService<Test, Long> commonService, TestService testService) {
        super(commonService);
        this.testService = testService;
    }

    @GetMapping("/test/{testId}/session/{sessionId}")
    public ResponseEntity<?> getTest(@PathVariable Long testId,
                                     @PathVariable Long sessionId,
                                     @RequestParam(defaultValue = "0") int skip,
                                     @RequestParam(defaultValue = "10") int take) {
        return ResponseEntity.ok(testService.getTestQuestionsPaginated(testId, sessionId, skip, take));
    }

    @PostMapping("/test/{id}/init")
    public ResponseEntity<?> startTest(@PathVariable(name = "id") Long testId,
                                       @RequestParam Long userId) {
        return ResponseEntity.ok(testService.startTest(userId, testId));
    }

    @PostMapping("/test/session/{sessionId}")
    public ResponseEntity<?> submitUserAnswers(@RequestBody UserAnswersRequest userAnswersRequest,
                                               @PathVariable Long sessionId) {
        try {
            testService.submitUserAnswers(userAnswersRequest, sessionId);
        } catch (TestProcessingException exception) {
            return ResponseEntity.badRequest()
                    .body(new TestProcessingException("Answers can not be submitted."));
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/test/session/{sessionId}/finalize")
    public ResponseEntity<?> finalizeTest(@RequestBody UserAnswersRequest userAnswersRequest,
                                          @PathVariable Long sessionId) {
        try {
            testService.submitUserAnswers(userAnswersRequest, sessionId);
        } catch (TestProcessingException exception) {
            return ResponseEntity.badRequest()
                    .body(new TestProcessingException("Answers can not be submitted."));
        }

        Long userScore = testService.calculateResult(sessionId);

        FinalizeTestResponse finalizeTestResponse;
        try {
            finalizeTestResponse = testService.finalizeTest(sessionId, userScore);
            if (finalizeTestResponse == null) {
                return ResponseEntity.badRequest()
                        .body(new TestProcessingException("Not all questions are answered."));
            }
        } catch (AdviceProcessingException exception) {
            return ResponseEntity.ok(userScore);
        }

        return ResponseEntity.ok(finalizeTestResponse);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getTestPageForUser(@PathVariable Long id,
                                                @RequestParam(defaultValue = "0") int skip,
                                                @RequestParam(defaultValue = "10") int take) {
        return ResponseEntity.ok(testService.getAllTestsForUser(id, skip, take));
    }

}
