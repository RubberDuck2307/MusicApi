package com.example.music.artist;

import com.example.music.album.AlbumDTO;
import com.example.music.song.SongDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor

@AllArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ArtistDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String nickname;
    private Date dob;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<SongDTO> songs;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<AlbumDTO> albums;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> songsId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> albumsId;

    @JsonIgnore
    public void setSongs(Set<SongDTO> songs){
        this.songs = songs;
    }

    @JsonIgnore
    public void setAlbums(Set<AlbumDTO> albums) {
        this.albums = albums;
    }
}
