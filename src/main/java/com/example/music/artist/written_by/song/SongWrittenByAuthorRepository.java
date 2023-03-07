package com.example.music.artist.written_by.song;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SongWrittenByAuthorRepository extends JpaRepository<SongWrittenByArtist, SongWrittenByArtistKey> {

}
