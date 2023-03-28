package com.example.music.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    private ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){

        return ResponseEntity.ok(authenticationService.register(request)) ;
    }

    @PostMapping("/login")
    private ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) throws CredentialException {

        return ResponseEntity.ok(authenticationService.login(request)) ;
    }

    @PostMapping("/admin/{id}")
    private ResponseEntity<String> promoteToAdmin(@PathVariable int id){
        return authenticationService.promoteToAdmin(id);
    }


}
