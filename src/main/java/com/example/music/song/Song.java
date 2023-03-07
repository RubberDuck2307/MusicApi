package com.example.music.song;

import com.example.music.album.Album;
import com.example.music.rating.song.SongRating;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(schema = "music")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToMany(mappedBy = "song")
    private Set<SongRating> ratings;

    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album;

    private String title;

    private Integer length;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    @Column(columnDefinition="TEXT")
    private String lyrics;

    public void addRating(SongRating rating){
        ratings.add(rating);
    }
}
