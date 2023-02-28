package com.example.music.rating.album;

import com.example.music.album.Album;
import com.example.music.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(schema = "music")
public class AlbumRating {

    @EmbeddedId
    private AlbumRatingKey id;

    @ManyToOne
    @MapsId("UserId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("AlbumId")
    @JoinColumn(name = "album_id")
    private Album album;

    int rating;


    public AlbumRating(Album album, User user, int rating) {
        id = new AlbumRatingKey();
        id.setAlbumId(album.getId());
        id.setUserId(user.getId());
        this.album = album;
        this.user = user;
        this.rating = rating;
    }

    public AlbumRating() {

    }
}
