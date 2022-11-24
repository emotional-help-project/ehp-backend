package com.epam.rd.service;

import com.epam.rd.model.dto.UserDto;
import com.epam.rd.model.entity.User;
import com.epam.rd.model.enumerations.Gender;
import com.epam.rd.payload.request.LoginRequest;
import com.epam.rd.payload.request.SignupRequest;
import com.epam.rd.payload.response.JWTTokenSuccessResponse;
import org.springframework.data.domain.Page;

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

    void deleteUser(Long id);

    UserDto getUserById(Long id);

    UserDto updateUser(UserDto userDto);

    Page<UserDto> getAllUsersPaginated(int pageNum, int pageSize);

    Page<UserDto> getAllUsersByGenderPaginated(Gender gender, int pageNum, int pageSize);

}
