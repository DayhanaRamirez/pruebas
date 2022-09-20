package com.javatraineeprogram.homework5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.*;
import java.io.Serializable;

public class UserDto implements Serializable {

    @Size(max = 50, message = "First name must be less than 50 characters")
    @NotBlank(message = "First name is required.")
    @Pattern(regexp = "([A-Z a-z])\\w+", message = "First name does not follow the correct format")
    private String firstName;

    @Size(max = 50, message = "Last name must be less than 50 characters")
    @NotBlank(message = "Last name is required.")
    @Pattern(regexp = "([A-Z a-z])\\w+" +
            "", message = "Last name does not follow the correct format")
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp = "\\+503 \\d{4} \\d{4}", message = "Phone number does not follow the correct format")
    private String phoneNumber;

    public UserDto(){
    }

    public UserDto(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}