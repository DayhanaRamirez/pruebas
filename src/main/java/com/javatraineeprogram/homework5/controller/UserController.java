package com.javatraineeprogram.homework5.controller;

import com.javatraineeprogram.homework5.dto.UserDto;
import com.javatraineeprogram.homework5.dto.UserEmailDto;
import com.javatraineeprogram.homework5.exception.CreateUserException;
import com.javatraineeprogram.homework5.exception.GetUserException;
import com.javatraineeprogram.homework5.exception.UpdateUserException;
import com.javatraineeprogram.homework5.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDto> getByEmail(@RequestParam String email) throws GetUserException {
        //return new ResponseEntity<>(userService.getByEmail(email), HttpStatus.OK);
        UserDto userDto = userService.getByEmail(email);
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody UserEmailDto userEmailDto) throws CreateUserException {
        userService.create(userEmailDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<HttpStatus> update(@PathVariable("email") String email, @Valid @RequestBody UserDto userDto) throws UpdateUserException {
        userService.update(email, userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}