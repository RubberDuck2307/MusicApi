package com.example.music.album;

import com.example.music.artist.Artist;
import com.example.music.rating.album.AlbumRating;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(schema = "music")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer amountOfSongs;

    @ManyToMany(mappedBy = "albumsWritten")
    private Set<Artist> artists;
    @OneToMany(mappedBy = "album")
    private Set<AlbumRating> ratings;

    public void addRating(AlbumRating rating){
        ratings.add(rating);

    }

}
