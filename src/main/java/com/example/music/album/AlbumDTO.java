package com.example.music.album;

import com.example.music.artist.Artist;
import com.example.music.artist.ArtistDTO;
import com.example.music.song.Song;
import com.example.music.song.SongDTO;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class AlbumDTO {
    private Integer id;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfRelease;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<SongDTO> songs;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> artistsId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<ArtistDTO> artists;

    @Override
    public String toString() {
        return "AlbumDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateOfRelease=" + dateOfRelease +
                ", artistsId=" + artistsId +
                '}';
    }
    @JsonIgnore
    public void setArtists(Set<ArtistDTO> artistsDTO){
        this.artists = artistsDTO;
    }

    @JsonIgnore
    public void setSongs(Set<SongDTO> songs){
        this.songs = songs;
    }

    @JsonIgnore
    public Set<Integer> getArtistsId()
    {
        return artistsId;
    }

}
