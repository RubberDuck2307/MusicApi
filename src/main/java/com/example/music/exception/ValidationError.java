package com.example.music.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class ValidationError {

    Map<String, String> errors;
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ValidationError(){
        timestamp = LocalDateTime.now();
    }

    public ValidationError(HttpStatus status) {
        this();
        this.status = status;
    }


}
