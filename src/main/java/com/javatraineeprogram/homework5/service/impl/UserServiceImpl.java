package com.javatraineeprogram.homework5.service.impl;

import com.javatraineeprogram.homework5.dto.UserDto;
import com.javatraineeprogram.homework5.dto.UserEmailDto;
import com.javatraineeprogram.homework5.entity.User;
import com.javatraineeprogram.homework5.exception.CreateUserException;
import com.javatraineeprogram.homework5.exception.GetUserException;
import com.javatraineeprogram.homework5.exception.UpdateUserException;
import com.javatraineeprogram.homework5.mapper.Mapper;
import com.javatraineeprogram.homework5.repository.UserRepository;
import com.javatraineeprogram.homework5.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(UserEmailDto userEmailDto) throws CreateUserException {
        if (userRepository.findByEmail(userEmailDto.getEmail()) != null) {
            throw new CreateUserException("The user already exists with the given email account");
        }

        return userRepository.save(Mapper.userEmailDtoToUserEntity(userEmailDto));
    }

    @Override
    public UserDto getByEmail(String email) throws GetUserException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new GetUserException("We could not find a user with the given email");
        }

        return Mapper.userEntityToUserDto(user);
    }

    @Override
    public User update(String email, UserDto userDto) throws UpdateUserException {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            return userRepository.save(user);
        } else{
            throw new UpdateUserException("We could not find a user with the given email");
        }
    }
}
