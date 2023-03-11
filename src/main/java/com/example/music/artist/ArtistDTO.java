package com.example.music.artist;

import com.example.music.album.AlbumDTO;
import com.example.music.song.SongDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
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
}
