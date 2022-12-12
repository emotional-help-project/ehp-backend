package com.epam.rd.service.impl;

import com.epam.rd.exceptions.UserProcessingException;
import com.epam.rd.model.dto.UserDto;
import com.epam.rd.model.entity.User;
import com.epam.rd.model.enumerations.URole;
import com.epam.rd.exceptions.UserExistException;
import com.epam.rd.model.mapper.UserMapper;
import com.epam.rd.model.search.SearchSpecification;
import com.epam.rd.payload.request.*;
import com.epam.rd.payload.response.JWTTokenSuccessResponse;
import com.epam.rd.payload.response.UpdateUserPasswordResponse;
import com.epam.rd.repository.UserRepository;
import com.epam.rd.security.JWTTokenProvider;
import com.epam.rd.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_DOESNT_EXIST_BY_EMAIL = "A user with this email does not exist";
    private static final String CANNOT_FIND_USER_BY_ID = "Cannot find user with ID=";
    private static final String UPDATE_EXCEPTION = "Can't update non existing data";

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
                .orElseThrow(() -> new UserProcessingException(USER_DOESNT_EXIST_BY_EMAIL));
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
            currentUser = userRepository.findByEmail(email).orElseThrow(() -> new UserProcessingException(USER_DOESNT_EXIST_BY_EMAIL));
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
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserProcessingException(USER_DOESNT_EXIST_BY_EMAIL));
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
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UserProcessingException(USER_DOESNT_EXIST_BY_EMAIL));

        return new JWTTokenSuccessResponse(true, jwt, user.getId(), user.getFirstName());
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserProcessingException(CANNOT_FIND_USER_BY_ID + id));
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) {
        User user;
        if (userRepository.existsById(userDto.getId())) {
            User userToBeUpdated = userMapper.toEntity(userDto);
            userToBeUpdated.setPassword(userRepository.findById(userDto.getId())
                    .orElseThrow(() -> new UserProcessingException(CANNOT_FIND_USER_BY_ID + userDto.getId())).getPassword());
            user = userRepository.save(userToBeUpdated);
        } else {
            log.error(UPDATE_EXCEPTION);
            throw new UserProcessingException(UPDATE_EXCEPTION);
        }
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<UserDto> getAllUsersPaginated(int pageNum, int pageSize) {
        Pageable pageable = createPageRequest(pageNum, pageSize);
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Transactional
    @Override
    public Page<UserDto> searchUser(SearchRequest request) {
        SearchSpecification<User> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return userRepository.findAll(specification, pageable).map(userMapper::toDto);
    }

    @Transactional
    @Override
    public UserDto updateUserProfile(UpdateUserProfileRequest updateUserProfile) {

        User updatedUser = userRepository.findById(updateUserProfile.getId())
                .orElseThrow(() -> new UserProcessingException(CANNOT_FIND_USER_BY_ID + updateUserProfile.getId()));

        if (updateUserProfile.getFirstName() != null) {
            updatedUser.setFirstName(updateUserProfile.getFirstName());
        }

        if (updateUserProfile.getLastName() != null) {
            updatedUser.setLastName(updateUserProfile.getLastName());
        }

        if (updateUserProfile.getEmail() != null) {
            updatedUser.setEmail(updateUserProfile.getEmail());
        }

        if (updateUserProfile.getGender() != null) {
            updatedUser.setGender(updateUserProfile.getGender());
        }

        if (updateUserProfile.getAge() != null) {
            updatedUser.setAge(updateUserProfile.getAge());
        }

        userRepository.save(updatedUser);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public UpdateUserPasswordResponse updateUserPassword(UpdateUserPasswordRequest updateUserPasswordRequest) {

        User updatedUser = userRepository.findById(updateUserPasswordRequest.getUserId())
                .orElseThrow(() -> new UserProcessingException(CANNOT_FIND_USER_BY_ID + updateUserPasswordRequest.getUserId()));

        if (updateUserPasswordRequest.getNewPassword() != null) {
            final String encodedPassword = passwordEncoder.encode(updateUserPasswordRequest.getNewPassword());
            updatedUser.setPassword(encodedPassword);
        }

        return new UpdateUserPasswordResponse()
                .setUserId(updatedUser.getId())
                .setNewPassword(updatedUser.getPassword());
    }

    private PageRequest createPageRequest(int pageNum, int pageSize) {
        return PageRequest.of(pageNum - 1, pageSize, Sort.by("id").descending());
    }
}
