package com.example.music.album;

import com.example.music.artist.Artist;
import com.example.music.artist.ArtistRepository;
import com.example.music.rating.album.AlbumRating;
import com.example.music.song.Song;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(schema = "music")
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfRelease;

    @ManyToMany(mappedBy = "albums",fetch = FetchType.LAZY)

    @JsonBackReference("artist_album")
    private Set<Artist> artists;
    @JsonIgnore
    @OneToMany(mappedBy = "album")
    private Set<AlbumRating> ratings;

    @JsonIgnore
    @OneToMany(mappedBy = "album")
    @JsonManagedReference("album_song")
    private Set<Song> songs;

    private String title;


    public Album(int id, String title, LocalDate dateOfRelease) {
        this.id = id;
        this.title = title;
        this.dateOfRelease = dateOfRelease;
    }

    public Album(String title, LocalDate dateOfRelease) {
        this.title = title;
        this.dateOfRelease = dateOfRelease;
    }

    public void addRating(AlbumRating rating) {
        ratings.add(rating);

    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) {
        songs.remove(song);
    }

    public void addArtist(Artist artist){
        artists.add(artist);
    }

    public void removeArtist(Artist artist){
        artists.remove(artist);
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
