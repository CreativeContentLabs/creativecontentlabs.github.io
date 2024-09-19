package com.cclabs.cclabPost.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private boolean isSuccess;
    private int statusCode;
    private String message;

    public ApiResponse(boolean isSuccess, int statusCode, String message) {
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.message = message;
    }
}
