package com.epam.rd.controller;

import com.epam.rd.model.dto.UserDto;
import com.epam.rd.payload.request.UpdateUserPasswordRequest;
import com.epam.rd.payload.request.UpdateUserProfileRequest;
import com.epam.rd.service.TestService;
import com.epam.rd.service.UserService;
import com.epam.rd.validations.ResponseErrorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/profile")
@CrossOrigin(maxAge = 36000)
public class UserProfileController {

    private final UserService userService;
    private final TestService testService;

    private final ResponseErrorValidation responseErrorValidation;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAllUsers();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateUserProfileRequest updateUserProfileRequest,
                                           BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        return ResponseEntity.ok(userService.updateUserProfile(updateUserProfileRequest));
    }

    @GetMapping(value = "/{id}/info")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/{userId}/map/{testId}")
    public ResponseEntity<?> getEmotionMapByTest(@PathVariable Long userId,
                                                 @PathVariable Long testId) {
        return ResponseEntity.ok(testService.getEmotionMapByTest(userId, testId));
    }

    @GetMapping("/{id}/statistics")
    public ResponseEntity<?> getUserEmotionStatistics(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getUserEmotionStatistics(id));
    }

    @GetMapping("/tests")
    public ResponseEntity<?> getTestsFinishedByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(testService.getTestsFinishedByUser(userId));
    }

    @PutMapping("/update/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest,
                                            BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        return ResponseEntity.ok(userService.updateUserPassword(updateUserPasswordRequest));
    }
}
