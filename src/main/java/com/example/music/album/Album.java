package com.example.music.album;

import com.example.music.artist.Artist;
import com.example.music.rating.album.AlbumRating;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
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
}
