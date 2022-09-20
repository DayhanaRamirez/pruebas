package com.javatraineeprogram.homework5.controller;

import com.javatraineeprogram.homework5.dto.UserDto;
import com.javatraineeprogram.homework5.dto.UserEmailDto;
import com.javatraineeprogram.homework5.entity.User;
import com.javatraineeprogram.homework5.exception.GetUserException;
import com.javatraineeprogram.homework5.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetByEmailSuccess() {
        //Arrange
        UserDto userDto = new UserDto("Dayhana", "Ramirez", "+503 4444 4444");

        when(userService.getByEmail(anyString())).thenReturn(userDto);

        //Act
        ResponseEntity<UserDto> responseEntity = userController.getByEmail("dayhana@gmail.com");

        //Assert
        verify(userService).getByEmail("dayhana@gmail.com");
        assertAll(
                () -> assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK)),
                () -> assertThat(responseEntity.getBody().getFirstName(), is("Dayhana")),
                () -> assertThat(responseEntity.getBody().getLastName(), is("Ramirez")),
                () -> assertThat(responseEntity.getBody().getPhoneNumber(), is("+503 4444 4444"))
        );
    }

    @Test
    void testGetByEmailFailure() {
        //Arrange
        when(userService.getByEmail(anyString())).thenThrow(new GetUserException("Hello world"));

        //Act
        final GetUserException exception = Assertions.assertThrows(GetUserException.class, () -> {
            userController.getByEmail("dayhana@gmail.com");
        });

        //Assert
        verify(userService).getByEmail("dayhana@gmail.com");

        assertAll(
                () -> assertThat(exception.getClass(), is(GetUserException.class)),
                () -> assertThat(exception.getMessage(), is("Hello world"))
        );

    }

    @Test
    void testSaveSuccessful() {
        UserEmailDto userEmailDto = new UserEmailDto("Dayhana", "Ramirez", "+503 4444 4444", "dayhana@gmail.com");
        when(userService.create(any(UserEmailDto.class))).thenReturn(new User(
                "dayhana@gmail.com",
                "Dayhana",
                "Ramirez",
                "+503 4444 4444")
        );

        ResponseEntity<HttpStatus> responseEntity = userController.save(userEmailDto);

        verify(userService).create(any(UserEmailDto.class));
        assertAll(
                () -> assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED))
        );
    }

    @Test
    void testUpdateSuccessful() {
        UserDto userDto = new UserDto("Marcela", "Cardona", "+503 5555 5555");
        when(userService.update(anyString(), any(UserDto.class))).thenReturn(new User(
                "dayhana@gmail.com",
                "Dayhana",
                "Ramirez",
                "+503 4444 4444")
        );

        ResponseEntity<HttpStatus> responseEntity = userController.update("dayhana@gmail.com", userDto);

        verify(userService).update(anyString(), any(UserDto.class));
        assertAll(
                () -> assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK))
        );
    }
}