package com.epam.rd.controller;

import com.epam.rd.model.entity.User;
import com.epam.rd.payload.response.JWTTokenSuccessResponse;
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


import javax.validation.Valid;
import java.security.Principal;


/**
 * Controller for user registration and login
 */
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
     * Endpoint for user authentication and login which response contains jwt token
     */
    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }

    /**
     * Register new user
     */
    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        User user = userService.createUser(signupRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<User> getUserByPrincipal(Principal principal) {
        if (principal != null && principal.getName() != null) {
            return ResponseEntity.ok(userService.getUserByPrincipal(principal));
        }
        return ResponseEntity.notFound().build();
    }


}
