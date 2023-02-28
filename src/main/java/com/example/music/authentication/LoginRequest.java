package com.example.music.authentication;


import lombok.Data;

@Data

public class LoginRequest {

    private String userEmail;
    private String password;
}
