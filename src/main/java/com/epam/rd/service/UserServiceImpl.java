package com.epam.rd.service;

import com.epam.rd.exceptions.UserProcessingException;
import com.epam.rd.model.dto.UserDto;
import com.epam.rd.model.entity.User;
import com.epam.rd.model.enumerations.URole;
import com.epam.rd.exceptions.UserExistException;
import com.epam.rd.model.mapper.UserMapper;
import com.epam.rd.payload.request.LoginRequest;
import com.epam.rd.payload.request.SignupRequest;
import com.epam.rd.payload.response.JWTTokenSuccessResponse;
import com.epam.rd.repository.UserRepository;
import com.epam.rd.security.JWTTokenProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_DOESNT_EXIST = "A user with this email does not exist";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setGender(signupRequest.getGender());
        user.setAge(signupRequest.getAge());
        user.getRoles().add(URole.USER);
        user.setRole(URole.USER);
        if (isUserExistByEmail(signupRequest.getEmail())) {
            throw new UserExistException("The user with email " + signupRequest.getEmail() + " already exists. Please check credentials.");
        }
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserDto getUserByPrincipal(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UserProcessingException(USER_DOESNT_EXIST));
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public UserDto getCurrentUser() {
        String email = getPrincipal();
        User currentUser = new User();
        if (email != null) {
            currentUser = userRepository.findByEmail(email).orElseThrow(() -> new UserProcessingException(USER_DOESNT_EXIST));
        }
        return userMapper.toDto(currentUser);
    }

    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserProcessingException(USER_DOESNT_EXIST));
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isUserExistByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional
    @Override
    public JWTTokenSuccessResponse getJwtAfterUserAuthentication(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return new JWTTokenSuccessResponse(true, jwt);
    }


}
