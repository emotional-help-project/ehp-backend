package com.epam.rd.service;

import com.epam.rd.model.dto.UserDto;
import com.epam.rd.model.entity.User;
import com.epam.rd.payload.request.LoginRequest;
import com.epam.rd.payload.request.SignupRequest;
import com.epam.rd.payload.response.JWTTokenSuccessResponse;

import java.security.Principal;
import java.util.List;

public interface UserService {

    UserDto createUser(SignupRequest signupRequest);

    UserDto getUserByPrincipal(Principal principal);

    List<UserDto> getAllUsers();

    UserDto getCurrentUser();

    UserDto getUserByEmail(String email);

    boolean isUserExistByEmail(String email);

    JWTTokenSuccessResponse getJwtAfterUserAuthentication(LoginRequest loginRequest);
}