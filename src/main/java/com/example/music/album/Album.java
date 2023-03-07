package com.example.music.album;

import com.example.music.artist.written_by.album.AlbumWrittenByArtist;
import com.example.music.rating.album.AlbumRating;
import com.example.music.song.Song;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(schema = "music")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfRelease;

    @OneToMany(mappedBy = "album")
    private Set<AlbumWrittenByArtist> writtenBy;
    @OneToMany(mappedBy = "album")
    private Set<AlbumRating> ratings;

    @OneToMany(mappedBy = "album")
    private Set<Song> songs;

    private String title;
    public void addRating(AlbumRating rating){
        ratings.add(rating);

    }

    public void addSong(Song song){
        songs.add(song);
    }

    public void removeSong(Song song){
        songs.remove(song);
    }

    public void addWrittenBy(AlbumWrittenByArtist albumWrittenByArtist){
        writtenBy.add(albumWrittenByArtist);
    }

    public AlbumDTO getDTO(){
        return new AlbumDTO(id, title, dateOfRelease);
    }
    public void removeWrittenBy(AlbumWrittenByArtist albumWrittenByArtist){
        writtenBy.remove(albumWrittenByArtist);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", dateOfRelease=" + dateOfRelease +
                ", title='" + title + '\'' +
                '}';
    }
}
