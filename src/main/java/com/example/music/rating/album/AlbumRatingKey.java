package com.example.music.rating.album;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class AlbumRatingKey implements Serializable {

    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "album_id")
    private Integer albumId;
}
