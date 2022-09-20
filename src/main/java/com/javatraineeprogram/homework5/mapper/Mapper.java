package com.javatraineeprogram.homework5.mapper;

import com.javatraineeprogram.homework5.dto.UserDto;
import com.javatraineeprogram.homework5.dto.UserEmailDto;
import com.javatraineeprogram.homework5.entity.User;

public class Mapper {

    public static User userEmailDtoToUserEntity(UserEmailDto userEmailDto){
        return new User(userEmailDto.getEmail(), userEmailDto.getFirstName(), userEmailDto.getLastName(), userEmailDto.getPhoneNumber());
    }

    public static UserDto userEntityToUserDto(User user){
        return new UserDto(user.getFirstName(), user.getLastName(), user.getPhoneNumber());
    }
}
