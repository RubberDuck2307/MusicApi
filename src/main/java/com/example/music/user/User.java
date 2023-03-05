package com.example.music.user;

import com.example.music.rating.album.AlbumRating;
import com.example.music.rating.SongRating;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@NoArgsConstructor
@Data
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

}
