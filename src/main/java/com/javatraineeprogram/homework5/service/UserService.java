package com.javatraineeprogram.homework5.service;

import com.javatraineeprogram.homework5.dto.UserDto;
import com.javatraineeprogram.homework5.dto.UserEmailDto;
import com.javatraineeprogram.homework5.entity.User;
import com.javatraineeprogram.homework5.exception.CreateUserException;
import com.javatraineeprogram.homework5.exception.GetUserException;
import com.javatraineeprogram.homework5.exception.UpdateUserException;

public interface UserService {

    User create(UserEmailDto userEmailDto) throws CreateUserException;

    UserDto getByEmail(String email) throws GetUserException;

    User update(String email, UserDto userDto) throws UpdateUserException;
}
