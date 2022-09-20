package com.javatraineeprogram.homework5.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class UserEmailDto extends UserDto implements Serializable {

    @Email(message = "Email is not in a correct format")
    @NotBlank(message = "Email is required.")
    private String email;


    public UserEmailDto(String firstName, String lastName, String phoneNumber, String email) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
