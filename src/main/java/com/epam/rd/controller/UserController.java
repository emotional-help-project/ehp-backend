package com.epam.rd.controller;

import com.epam.rd.model.dto.UserDto;
import com.epam.rd.payload.request.SearchRequest;
import com.epam.rd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("check/{email}")
    public ResponseEntity<Boolean> checkIfUserExistByEmail(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(userService.isUserExistByEmail(email));
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity
                .ok(userService.updateUser(userDto));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public Page<UserDto> getAllUsersPaginated(@RequestParam(required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(required = false, defaultValue = "5") int pageSize) {
        return userService.getAllUsersPaginated(pageNum, pageSize);
    }

    @PostMapping(value = "/search")
    public Page<UserDto> search(@RequestBody SearchRequest request) {
        return userService.searchUser(request);
    }

}
