package com.example.music.song;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Data

public class SongDTO {

    private Integer id;

    private String title;

    private Integer length;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    @Column(columnDefinition="TEXT")
    private String lyrics;

}
