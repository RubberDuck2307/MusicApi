package com.example.music.artist.written_by.song;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongWrittenByArtistKey implements Serializable {
    @Column(name = "artist_id")
    private Integer artistId;
    @Column(name = "artist_id")
    private Integer songId;
}
