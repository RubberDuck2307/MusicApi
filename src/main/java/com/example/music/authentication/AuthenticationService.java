package com.example.music.authentication;

import com.example.music.exception.custom_exceptions.InvalidDataException;
import com.example.music.exception.custom_exceptions.UserEmailTakenException;
import com.example.music.jwt.JwtService;
import com.example.music.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.CredentialException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserCredentialsRepository credentialsRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request){
        if (credentialsRepository.findByUserEmail(request.getUserEmail()).isPresent())
            throw new UserEmailTakenException();

        if (request.getUserEmail() == null || request.getUserEmail().length() < 1)
            throw new InvalidDataException();

        if (request.getPassword() == null || request.getPassword().length() < 1)
            throw new InvalidDataException();

        UserCredentials userCredentials = UserCredentials
                .builder()
                .userEmail(request.getUserEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();


        User user = new User();
        user.setUserCredentials(userCredentials);
        userCredentials.setUser(user);
        userRepository.save(user);
        credentialsRepository.save(userCredentials);

        String token = jwtService.generateToken(userCredentials);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse login(LoginRequest request) throws CredentialException {
        if (request.getUserEmail() == null || request.getUserEmail().length() < 1)
            throw new CredentialException();

        if (request.getPassword() == null || request.getPassword().length() < 1)
            throw new CredentialException();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserEmail(), request.getPassword()));
        UserCredentials user = credentialsRepository.findByUserEmail(request.getUserEmail()).orElseThrow();
        return new AuthenticationResponse(jwtService.generateToken(user));


    }
    @Transactional
    public ResponseEntity<String> promoteToAdmin(int id){
        UserCredentials userCredentials = credentialsRepository.findById(id).orElseThrow();
        userCredentials.setRole(Role.ROLE_ADMIN);
        return new ResponseEntity<>("The user has been set to admin", HttpStatus.OK);
    }

}
