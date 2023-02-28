package com.example.music.authentication;

import lombok.Data;

@Data
public class RegisterRequest {
    private String password;
    private String userEmail;

}
