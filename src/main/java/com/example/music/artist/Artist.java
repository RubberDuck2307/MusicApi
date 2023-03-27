package com.example.music.artist;


import com.example.music.album.Album;
import com.example.music.song.Song;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(schema = "music")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "music", name = "artist_album",
            joinColumns = {
                    @JoinColumn(name = "artist_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "album_id", referencedColumnName = "id")
            }
    )
    @JsonManagedReference("artist_album")
    private Set<Album> albums;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(schema = "music", name = "artist_song",
            joinColumns = {
                    @JoinColumn(name = "artist_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "song_id", referencedColumnName = "id")
            }
    )
    @JsonManagedReference("artist_song")
    private Set<Song> songs;

    public void addAlbum(Album album){
        albums.add(album);
    }

    public void removeAlbum(Album album){
        albums.remove(album);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

}
