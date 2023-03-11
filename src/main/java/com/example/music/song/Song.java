package com.example.music.song;

import com.example.music.album.Album;
import com.example.music.artist.Artist;
import com.example.music.rating.song.SongRating;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@RequiredArgsConstructor
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
    @JsonBackReference("album_song")

    private Album album;

    private String title;

    private Integer length;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;

    @Column(columnDefinition="TEXT")
    private String lyrics;

    @JsonBackReference("artist_song")
    @ManyToMany(mappedBy = "songs")

    private Set<Artist> artists;
    public void addRating(SongRating rating){
        if (ratings == null) ratings = new HashSet<>();
        ratings.add(rating);
    }

    public void addWrittenBy(Artist artist){
        if (artists == null) artists = new HashSet<>();
        artists.add(artist);
    }

    public void removeWrittenBy(Artist artist){
        if (artists == null) artists = new HashSet<>();
        artists.remove(artist);
    }

}
