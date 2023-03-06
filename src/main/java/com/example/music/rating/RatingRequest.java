package com.example.music.rating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;



@Data
public class RatingRequest {

    private String review;

    @Min(value = 1, message = "The rating value has to be between 1 and 10")
    @Max(value = 10, message = "The rating value has to be between 1 and 10")
    private int rating;


}
