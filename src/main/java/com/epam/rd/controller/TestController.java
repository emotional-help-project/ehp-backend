package com.epam.rd.controller;

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

    @GetMapping("/test/{id}")
    public ResponseEntity<?> getTest(@PathVariable Long id,
                                     @RequestParam(defaultValue = "0") int skip,
                                     @RequestParam(defaultValue = "10") int take) {
        return ResponseEntity.ok(testService.getTestQuestionsPaginated(id, skip, take));
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
        FinalizeTestResponse finalizeTestResponse = testService.finalizeTest(sessionId);
        if (finalizeTestResponse == null) {
            return ResponseEntity.badRequest()
                    .body(new TestProcessingException("Not all questions are answered."));
        }
        return ResponseEntity.ok(finalizeTestResponse);
    }


}
