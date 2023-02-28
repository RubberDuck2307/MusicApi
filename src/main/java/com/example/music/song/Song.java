package com.example.music.song;

import com.example.music.rating.SongRating;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(schema = "music")
public class Song {

    @Id
    private Integer id;
    @OneToMany(mappedBy = "song")
    private Set<SongRating> ratings;

}
