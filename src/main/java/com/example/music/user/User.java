package com.example.music.user;

import com.example.music.rating.album.AlbumRating;
import com.example.music.rating.song.SongRating;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@Table( schema = "music")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "user")
    private Set<AlbumRating> albumRatings;

    @OneToMany(mappedBy = "user")
    private Set<SongRating> songRatings;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserCredentials userCredentials;

    public User(Integer id){
        this.id = id;
    }

    public void addAlbumRating(AlbumRating rating){
        albumRatings.add(rating);
    }

    public void addSongRating(SongRating rating){
        songRatings.add(rating);
    }

}
