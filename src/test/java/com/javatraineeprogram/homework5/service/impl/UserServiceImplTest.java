package com.javatraineeprogram.homework5.service.impl;

import com.javatraineeprogram.homework5.dto.UserDto;
import com.javatraineeprogram.homework5.dto.UserEmailDto;
import com.javatraineeprogram.homework5.entity.User;
import com.javatraineeprogram.homework5.exception.CreateUserException;
import com.javatraineeprogram.homework5.exception.GetUserException;
import com.javatraineeprogram.homework5.exception.UpdateUserException;
import com.javatraineeprogram.homework5.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSuccessfulUser() {
        UserEmailDto userEmailDto = new UserEmailDto(
                "Dayhana",
                "Ramirez",
                "+503 4444 4444",
                "dayhana@gmail.com"
        );
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(new User(
                "dayhana@gmail.com",
                "Dayhana",
                "Ramirez",
                "+503 4444 4444")
        );

        User userResponse = userService.create(userEmailDto);

        verify(userRepository).findByEmail("dayhana@gmail.com");
        verify(userRepository).save(any(User.class));
        assertAll(
                () -> assertThat(userResponse, notNullValue()),
                () -> assertThat(userResponse.getId(), nullValue()),
                () -> assertThat(userResponse.getFirstName(), is("Dayhana")),
                () -> assertThat(userResponse.getLastName(), is("Ramirez")),
                () -> assertThat(userResponse.getPhoneNumber(), is("+503 4444 4444"))
        );
    }

    @Test
    void testFailureCreateUser() {
        UserEmailDto userEmailDto = new UserEmailDto(
                "Dayhana",
                "Ramirez",
                "+503 4444 4444",
                "dayhana@gmail.com"
        );
        when(userRepository.findByEmail(anyString())).thenReturn(new User(
                "dayhana@gmail.com",
                "Dayhana",
                "Ramirez",
                "+503 4444 4444")
        );

        final CreateUserException exception = Assertions.assertThrows(CreateUserException.class, () -> {
            userService.create(userEmailDto);
        });

        verify(userRepository).findByEmail("dayhana@gmail.com");
        verify(userRepository, times(0)).save(any(User.class));
        assertAll(
                () -> assertThat(exception.getClass(), is(CreateUserException.class)),
                () -> assertThat(exception.getMessage(), is("The user already exists with the given email account"))
        );
    }

    @Test
    void testGetByEmailSuccess() {
        when(userRepository.findByEmail(anyString())).thenReturn(new User(
                "dayhana@gmail.com",
                "Dayhana",
                "Ramirez",
                "+503 4444 4444")
        );

        UserDto userDtoResponse = userService.getByEmail("dayhana@gmail.com");

        verify(userRepository).findByEmail("dayhana@gmail.com");
        assertAll(
                () -> assertThat(userDtoResponse.getFirstName(), is("Dayhana")),
                () -> assertThat(userDtoResponse.getLastName(), is("Ramirez")),
                () -> assertThat(userDtoResponse.getPhoneNumber(), is("+503 4444 4444"))
        );
    }

    @Test
    void testGetByEmailFailure() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        final GetUserException exception = Assertions.assertThrows(GetUserException.class, () -> {
            userService.getByEmail("dayhana@gmail.com");
        });

        verify(userRepository).findByEmail("dayhana@gmail.com");
        assertAll(
                () -> assertEquals(GetUserException.class, exception.getClass()),
                () -> assertEquals("We could not find a user with the given email", exception.getMessage())
        );
    }

    @Test
    void testUpdateSuccess() {
        //Arrange
        UserDto userDto = new UserDto("Marcela", "Cardona", "+503 5555 5555");
        when(userRepository.findByEmail(anyString())).thenReturn(new User(
                "dayhana@gmail.com",
                "Dayhana",
                "Ramirez",
                "+503 4444 4444")
        );
        when(userRepository.save(any(User.class))).thenReturn(new User(
                "dayhana@gmail.com",
                "Marcela",
                "Cardona",
                "+503 5555 5555"
        ));

        //Act
        User userResponse = userService.update("dayhana@gmail.com", userDto);

        //Assert
        verify(userRepository).findByEmail("dayhana@gmail.com");
        verify(userRepository).save(any(User.class));
        assertAll(
                () -> assertThat(userResponse, notNullValue()),
                () -> assertThat(userResponse.getFirstName(), is("Marcela")),
                () -> assertThat(userResponse.getLastName(), is("Cardona")),
                () -> assertThat(userResponse.getPhoneNumber(), is("+503 5555 5555"))
        );
    }

    @Test
    void testUpdateFailure() {
        UserDto userDto = new UserDto("Marcela", "Cardona", "+503 5555 5555");
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        final UpdateUserException exception = Assertions.assertThrows(UpdateUserException.class, () -> {
            userService.update("dayhana@gmail.com", userDto);
        });

        verify(userRepository).findByEmail("dayhana@gmail.com");
        verify(userRepository, times(0)).save(any(User.class));
        assertAll(
                () -> assertThat(exception.getClass(), is(UpdateUserException.class)),
                () -> assertThat(exception.getMessage(), is("We could not find a user with the given email"))
        );
    }

}
