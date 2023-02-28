package com.example.music.user;

import com.example.music.rating.album.AlbumRating;
import com.example.music.rating.SongRating;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Table( schema = "music")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "user")
    Set<AlbumRating> albumRatings;

    @OneToMany(mappedBy = "user")
    Set<SongRating> songRatings;

}
