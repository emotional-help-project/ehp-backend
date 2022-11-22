package com.epam.rd.controller;

import com.epam.rd.entity.User;
import com.epam.rd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


/**
 *
 * Profil ma'lumotlarini tahrirlash uchun controller. (Ro'yhatdan o'tish shart emas !!!)
 *
 *
 * */


@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public List<User> getAll (Principal principal){
        return userService.getAll();
    }
}
