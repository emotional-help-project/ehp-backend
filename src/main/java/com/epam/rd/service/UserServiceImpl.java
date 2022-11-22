package com.epam.rd.service;

import com.epam.rd.exceptions.UserProcessingException;
import com.epam.rd.model.entity.User;
import com.epam.rd.model.enumerations.URole;
import com.epam.rd.exceptions.UserExistException;
import com.epam.rd.payload.request.SignupRequest;
import com.epam.rd.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_DOESNT_EXIST = "A user with this email does not exist";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setGender(signupRequest.getGender());
        user.setAge(signupRequest.getAge());
        user.getRoles().add(URole.USER);
        user.setRole(URole.USER);
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserExistException("The user with email " + user.getUsername() + " already exists. Please check credentials.");
        }
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UserProcessingException(USER_DOESNT_EXIST));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getCurrentUser() {
        String email = getPrincipal();
        if (email != null)
            return userRepository.findByEmail(email).orElseThrow(() -> new UserProcessingException(USER_DOESNT_EXIST));
        return null;
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

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserProcessingException(USER_DOESNT_EXIST));
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


}
