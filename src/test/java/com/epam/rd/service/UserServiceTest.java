package com.epam.rd.service;

import com.epam.rd.UnitTestExpectedDtoSupplier;
import com.epam.rd.UnitTestExpectedEntitySupplier;
import com.epam.rd.UnitTestRequestSupplier;
import com.epam.rd.exceptions.UserExistException;
import com.epam.rd.exceptions.UserProcessingException;
import com.epam.rd.model.dto.UserDto;
import com.epam.rd.model.entity.User;
import com.epam.rd.model.mapper.UserMapper;
import com.epam.rd.model.mapper.UserMapperImpl;
import com.epam.rd.payload.request.SignupRequest;
import com.epam.rd.repository.UserRepository;
import com.epam.rd.security.JwtUtil;
import com.epam.rd.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final String CANNOT_FIND_USER_BY_ID = "Cannot find user with ID=";
    private static final String CAN_NOT_DELETE_USER = "Can't delete user with ID=";

    private static final Long NOT_EXIST_ID = -1L;
    private static final Long DEFAULT_USER_ID = 123L;

    @Mock
    private UserRepository userRepositoryMock;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    private UserMapper userMapper;
    private UserService userService;
    private UserDto userDto;
    private User expectedUser;
    private SignupRequest signupRequest;


    @BeforeEach
    public void setUp() {
        userMapper = new UserMapperImpl();
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepositoryMock, passwordEncoder, jwtTokenProvider,
                authenticationManager, userMapper);
        userDto = UnitTestExpectedDtoSupplier.createUserDto();
        expectedUser = UnitTestExpectedEntitySupplier.createUser();
        signupRequest = UnitTestRequestSupplier.signupRequest();
    }

    @Test
    void testGetAllUsers_whenCalled_callsRepo() {

        userService.getAllUsers();
        verify(userRepositoryMock, times(1)).findAll();
    }

    @Test
    void testCreateUser_whenRepoThrows_throwsException() {
        when(userRepositoryMock.findByEmail(userDto.getEmail())).thenReturn(
                Optional.of(userMapper.toEntity(userDto)));

        assertThrows(UserExistException.class, () -> userService.createUser(signupRequest));
    }

    @Test
    void testCreateUser_whenThrowsUserExistsException_shouldShowExceptionMessage() {
        String messageNotToGet = "aaaaa";
        when(userRepositoryMock.findByEmail(userDto.getEmail())).thenReturn(
                Optional.of(userMapper.toEntity(userDto)));

        UserExistException exception = assertThrows(UserExistException.class,
                () -> userService.createUser(signupRequest));

        assertEquals("The user with email " + signupRequest.getEmail() + " already exists. Please check credentials.", exception.getMessage());
        assertNotEquals(messageNotToGet, exception.getMessage());

    }

    @Test
    void testCreateUser_whenCalled_callsRepo() throws UserExistException {

        userService.createUser(signupRequest);
        verify(userRepositoryMock, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById_whenCalled_RepoCalled() {
        User user = userMapper.toEntity(userDto);

        when(userRepositoryMock.findById(any())).thenReturn(Optional.of(user));

        userService.getUserById(DEFAULT_USER_ID);
        verify(userRepositoryMock, times(1)).findById(DEFAULT_USER_ID);
    }

    @Test
    void testGetUserById_whenRepoThrows_throwsException() {

        when(userRepositoryMock.findById(DEFAULT_USER_ID)).thenThrow(
                new UserProcessingException(CANNOT_FIND_USER_BY_ID + DEFAULT_USER_ID));

        assertThrowsExactly(UserProcessingException.class, () -> userService.getUserById(DEFAULT_USER_ID));
    }

    @Test
    void testGetUserById_whenThrowsNotFoundException_ShouldShowExceptionMessage() {
        String messageNotToGet = "aaaaa";
        long userId = 1125L;

        when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());

        UserProcessingException exception = assertThrows(UserProcessingException.class,
                () -> userService.getUserById(userId));
        assertEquals(CANNOT_FIND_USER_BY_ID + userId, exception.getMessage());
        assertNotEquals(messageNotToGet, exception.getMessage());

    }

    @Test
    void testGetUserById_whenCalled_returnsCorrectUser() {
        long userId = 123L;

        when(userRepositoryMock.findById(userId)).thenReturn(
                Optional.of(userMapper.toEntity(userDto)));

        UserDto result = userService.getUserById(userId);

        assertEquals(userDto.getId(), result.getId());
        assertEquals(userDto.getEmail(), result.getEmail());
        assertEquals(userDto.getFirstName(), result.getFirstName());
        assertEquals(userDto.getLastName(), result.getLastName());
        assertEquals(userDto.getAge(), result.getAge());
        assertEquals(userDto.getGender(), result.getGender());
        assertEquals(userDto.getRole(), result.getRole());
    }

    @Test
    void updateUserProfileTest_ShouldUpdateSpecifiedFields() {

        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(expectedUser));
        UserDto actualDto = userService.updateUserProfile(UnitTestRequestSupplier.updateUserProfileRequest());

        verify(userRepositoryMock).findById(expectedUser.getId());
        verify(userRepositoryMock).save(any(User.class));

        assertEquals("smith.r@gmail.com", actualDto.getEmail());
        assertEquals(27, actualDto.getAge());
    }

    @Test
    void testDeleteUser_WhenUserCanBeDeleted_RepoCalled() {
        userService.deleteUser(DEFAULT_USER_ID);
        verify(userRepositoryMock, times(1)).deleteById(DEFAULT_USER_ID);
    }

    @Test
    void testDeleteUser_WhenUserNotFound_ShouldThrowException() {
        doThrow(UserProcessingException.class)
                .when(userRepositoryMock)
                .deleteById(NOT_EXIST_ID);

        assertThrows(UserProcessingException.class, () -> userService.deleteUser(NOT_EXIST_ID));
    }

    @Test
    void testDeleteUser_whenThrowsUserNotFoundException_shouldShowExceptionMessage() {
        String messageNotToGet = "aaaaa";
        doThrow(UserProcessingException.class)
                .when(userRepositoryMock)
                .deleteById(NOT_EXIST_ID);

        UserProcessingException exception = assertThrows(UserProcessingException.class,
                () -> userService.deleteUser(NOT_EXIST_ID));

        assertEquals(CAN_NOT_DELETE_USER + NOT_EXIST_ID, exception.getMessage());
        assertNotEquals(messageNotToGet, exception.getMessage());
    }



}
