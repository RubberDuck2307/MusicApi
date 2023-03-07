package com.example.music.artist;


import com.example.music.artist.written_by.album.AlbumWrittenByArtist;
import com.example.music.artist.written_by.song.SongWrittenByArtist;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(schema = "music")
public class Artist {
    @Id
    @SequenceGenerator(sequenceName = "artist_sequence",
    name = "artist_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "artist_sequence")
    private Integer id;
    private String firstName;
    private String lastName;
    private String nickname;
    private Date dob;
    @OneToMany(mappedBy = "artist")
    private Set<AlbumWrittenByArtist> albumsWritten;

    @OneToMany(mappedBy = "artist")
    private Set<SongWrittenByArtist> songsWritten;


    public void addAlbum(AlbumWrittenByArtist album){
        albumsWritten.add(album);
    }
    public void removeAlbum(AlbumWrittenByArtist albumWrittenByArtist){
        albumsWritten.remove(albumWrittenByArtist);
    }

    public void addSong(SongWrittenByArtist song){
        songsWritten.add(song);
    }
    public void removeSong(SongWrittenByArtist song){
        songsWritten.remove(song);
    }

}
