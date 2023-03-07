package com.example.music.artist.written_by.album;

import com.example.music.album.Album;
import com.example.music.artist.Artist;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "music")

public class AlbumWrittenByArtist {
    @EmbeddedId
    private AlbumWrittenByArtistKey key;

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne
    @MapsId("artistId")
    @JoinColumn(name = "artist_id")
    private Artist artist;

}
