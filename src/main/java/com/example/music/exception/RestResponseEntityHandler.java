package com.example.music.exception;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.CredentialException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( value = UserEmailTakenException.class)
    protected ResponseEntity<Object> handleTakenEmail(){
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage("The email has been already taken");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = InvalidDataException.class)
    protected ResponseEntity<Object> handleInvalidData(){
        ApiError apiError = new ApiError(HttpStatus.CONFLICT);
        apiError.setMessage("The sent data are invalid");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = CredentialException.class)
    protected ResponseEntity<Object> handleCredentialException(){
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
        apiError.setMessage("Either the email or password are not correct");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = io.jsonwebtoken.security.SignatureException.class)
    protected ResponseEntity<Object> handleTokenException(){
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
        apiError.setMessage("The authorization token is invalid");
        return buildResponseEntity(apiError);
    }

    @Override

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST);
        validationError.setErrors(errors);

        return buildResponseEntity(validationError);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<Object> handleItemNotFound(){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage("The item with such id has not been found");
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private ResponseEntity<Object> buildResponseEntity(ValidationError validationError){
        return new ResponseEntity<>(validationError, validationError.getStatus());
    }
}
