package com.example.music.artist;


import com.example.music.album.Album;
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
    @ManyToMany
    @JoinTable(name = "Album_written",
    joinColumns = @JoinColumn(name = "artist_id"),
    inverseJoinColumns = @JoinColumn(name = "album_id"),
    schema = "music")
    private Set<Album> albumsWritten;
}
