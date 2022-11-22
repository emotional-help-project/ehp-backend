package com.epam.rd.controller;

import com.epam.rd.dto.AccountDTO;
import com.epam.rd.entity.User;
import com.epam.rd.payload.reponse.JWTTokenSuccessResponse;
import com.epam.rd.payload.request.LoginRequest;
import com.epam.rd.payload.request.SignupRequest;
import com.epam.rd.security.JWTTokenProvider;
import com.epam.rd.service.UserService;
import com.epam.rd.validations.ResponseErrorValidation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.security.Principal;


/**
 *
 *
 * Controller for registration and login
 *
 *
* */

@CrossOrigin(maxAge = 36000)
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ResponseErrorValidation responseErrorValidation;
    private final UserService userService;

    /**
     *
     * api/account/signin- api for log in system it response jwt token
     */
    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt =jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    /**
     *
     * register
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.createUser(signupRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("check/{username}")
    public ResponseEntity<Boolean> check(@PathVariable(name = "username") String username){
        return ResponseEntity.ok(userService.check(username));
    }

    @GetMapping
    public ResponseEntity<User> getUser(Principal principal){
        if(principal != null && principal.getName() != null)
        return ResponseEntity.ok(userService.getByPrincipal(principal));
        return ResponseEntity.notFound().build();
    }


//    @PostMapping("/update")
//    public ResponseEntity<AccountDTO> updateUser(@RequestBody AccountDTO accountDTO, Principal principal){
//        return ResponseEntity.ok(userService.updateUser(accountDTO, principal));
//    }

//    @PostMapping("/updatePassword")
//    public ResponseEntity<Boolean> updatePassword(@RequestBody PasswordDTO passwordDTO, Principal principal){
//        return ResponseEntity.ok(userService.updatePassword(passwordDTO, principal));
//    }




}
