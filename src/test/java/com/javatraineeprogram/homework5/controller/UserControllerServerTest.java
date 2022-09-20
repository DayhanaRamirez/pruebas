package com.javatraineeprogram.homework5.controller;


import com.javatraineeprogram.homework5.dto.UserDto;
import com.javatraineeprogram.homework5.dto.UserEmailDto;
import com.javatraineeprogram.homework5.entity.User;
import com.javatraineeprogram.homework5.exception.GetUserException;
import com.javatraineeprogram.homework5.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest
public class UserControllerServerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnUserDtoWhenGetUserByEmail() throws Exception {
        UserDto userDto = new UserDto("Dayhana", "Ramirez", "+503 4444 4444");
        when(userService.getByEmail(anyString())).thenReturn(userDto);

        this.mockMvc.perform(get("/user/by-email?email=dayhana@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(
                        content()
                        .json("{\"firstName\":\"Dayhana\",\"lastName\":\"Ramirez\",\"phoneNumber\":\"+503 4444 4444\"}")
                );
    }

    @Test
    public void shouldReturnStatusCreatedWhenSaveUser() throws Exception {
        when(userService.create(any(UserEmailDto.class))).thenReturn(new User());

        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"firstName\":\"Dayhana\",\"lastName\":\"Ramirez\",\"phoneNumber\":\"+503 4444 4444\",\"email\":\"dayhana@gmail.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnStatusOkWhenUpdatedUser() throws Exception {
        when(userService.update(anyString(), any(UserDto.class))).thenReturn(new User());

        this.mockMvc.perform(put("/user/dayhana@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"firstName\":\"Dayhana\",\"lastName\":\"Ramirez\",\"phoneNumber\":\"+503 4444 4444\"}"))
                .andExpect(status().isOk());
    }

}
