package com.epam.rd.controller;

import com.epam.rd.model.entity.User;
import com.epam.rd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("check/{email}")
    public ResponseEntity<Boolean> checkIfUserExistByEmail(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(userService.isUserExistByEmail(email));
    }
}
