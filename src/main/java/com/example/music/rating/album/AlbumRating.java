package com.example.music.rating.album;

import com.example.music.album.Album;
import com.example.music.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Builder
@AllArgsConstructor
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

    String review;
    int rating;


    public String toString(){
        return id.toString() + review + rating;
    }
}
