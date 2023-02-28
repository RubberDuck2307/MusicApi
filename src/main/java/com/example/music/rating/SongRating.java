package com.example.music.rating;

import com.example.music.song.Song;
import com.example.music.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
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
}
