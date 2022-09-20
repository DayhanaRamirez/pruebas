package com.javatraineeprogram.homework5.controller.responses;

public class UserErrorResponse {
    private Integer status;
    private String message;

    public UserErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
