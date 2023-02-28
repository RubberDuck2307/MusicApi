package com.example.music.artist;

import lombok.Data;

import java.util.Date;

@Data
public class ArtistDTO {
    private String firstName;
    private String lastName;
    private Date dob;
}
