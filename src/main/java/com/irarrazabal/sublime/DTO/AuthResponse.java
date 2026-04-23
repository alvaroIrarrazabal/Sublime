package com.irarrazabal.sublime.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String accessToken;
    private String role;
    private long expiresIn;

    public AuthResponse(String accessToken, String role, long expiresIn) {
        this.accessToken = accessToken;
        this.role = role;
        this.expiresIn = expiresIn;
    }
}

