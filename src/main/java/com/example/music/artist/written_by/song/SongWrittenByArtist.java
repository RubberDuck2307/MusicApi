package com.example.music.artist.written_by.song;

import com.example.music.artist.Artist;
import com.example.music.song.Song;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(schema = "music")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SongWrittenByArtist {
    @EmbeddedId
    SongWrittenByArtistKey key;


    @ManyToOne
    @MapsId("songId")
    @JoinColumn(name = "song_id")
    Song song;

    @ManyToOne
    @MapsId("artistId")
    @JoinColumn(name = "artist_id")
    Artist artist;
}
