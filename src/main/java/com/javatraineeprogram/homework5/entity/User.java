package com.javatraineeprogram.homework5.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Validated
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "Email is not in a correct format")
    @NotBlank(message = "Email is required.")
    @Column(unique = true, name = "email")
    private String email;

    @Size(max = 50, message = "First name must be less than 50 characters")
    @NotBlank(message = "First name is required.")
    @Pattern(regexp = "([A-Z a-z])\\w+", message = "First name does not follow the correct format")
    @Column(name = "firstName")
    private String firstName;

    @Size(max = 50, message = "Last name must be less than 50 characters")
    @NotBlank(message = "Last name is required.")
    @Pattern(regexp = "([A-Z a-z])\\w+" +
            "", message = "Last name does not follow the correct format")
    @Column(name = "lastName")
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp = "\\+503 \\d{4} \\d{4}", message = "Phone number does not follow the correct format")
    @Column(name = "phoneNumber")
    private String phoneNumber;

    public User(String email, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public User(){

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }
}
