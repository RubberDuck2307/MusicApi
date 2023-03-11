package com.example.music.song;

import com.example.music.album.Album;
import com.example.music.artist.Artist;
import com.example.music.artist.ArtistDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class SongDTO {

    private Integer id;
    private String title;
    private Integer length;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;
    private String lyrics;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<ArtistDTO> artists;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer albumId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Album album;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Integer> artistsId;
    @JsonIgnore
    public Integer getAlbumId() {
        return albumId;
    }
}
