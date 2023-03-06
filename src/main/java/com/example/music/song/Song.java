package com.example.music.song;

import com.example.music.rating.song.SongRating;
import jakarta.persistence.*;
import lombok.Data;

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

    public void addRating(SongRating rating){
        ratings.add(rating);
    }
}
