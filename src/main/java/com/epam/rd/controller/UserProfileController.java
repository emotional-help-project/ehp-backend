package com.epam.rd.controller;

import com.epam.rd.model.dto.UserDto;
import com.epam.rd.payload.request.SearchRequest;
import com.epam.rd.payload.request.UpdateUserProfileRequest;
import com.epam.rd.service.TestService;
import com.epam.rd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/profile")
public class UserProfileController {

    private final UserService userService;
    private final TestService testService;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAllUsers();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UpdateUserProfileRequest updateUserProfileRequest) {
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
}
