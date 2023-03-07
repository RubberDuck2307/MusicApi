package com.example.music.artist.written_by.album;

import com.example.music.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumWrittenByArtistRepository extends JpaRepository<AlbumWrittenByArtist, AlbumWrittenByArtistKey> {

    public List<AlbumWrittenByArtist> findAllByArtist(Artist artist);

}
