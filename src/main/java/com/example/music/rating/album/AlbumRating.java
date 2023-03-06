package com.example.music.rating.album;

import com.example.music.album.Album;
import com.example.music.user.User;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "music")
public class AlbumRating {

    @EmbeddedId
    private AlbumRatingKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "album_id")
    private Album album;


    String review;
    int rating;

    public AlbumRating(User user, Album album, String review, int rating) {
        this.user = user;
        this.album = album;
        this.review = review;
        this.rating = rating;
    }


    public String toString(){
        return   review + rating;
    }
}
