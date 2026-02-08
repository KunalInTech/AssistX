package com.assistx.dto;

public class LoginResponse {

    private boolean success;
    private String message;
    private String role;

    public LoginResponse(boolean success, String message, String redirectUrl) {
        this.success = success;
        this.message = message;
        this.role = redirectUrl;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }
}
