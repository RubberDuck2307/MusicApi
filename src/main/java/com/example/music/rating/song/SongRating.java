package com.example.music.rating.song;

import com.example.music.song.Song;
import com.example.music.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(schema = "music")
public class SongRating {

    @EmbeddedId
    private SongRatingKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("song_id")
    @JoinColumn(name = "song_id")
    private Song song;

    private int value;
    private String description;

    public SongRating() {

    }
}
