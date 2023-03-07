package com.example.music.artist.written_by.album;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@Embeddable
public class AlbumWrittenByArtistKey implements Serializable {
@Column (name = "artist_id")
private int artistId;

@Column(name = "album_id")
private int albumId;


    public AlbumWrittenByArtistKey() {

    }
}
