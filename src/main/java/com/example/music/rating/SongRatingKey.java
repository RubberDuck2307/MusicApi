package com.example.music.rating;

import com.example.music.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SongRatingKey implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "song_id")
    private Integer songId;


}
