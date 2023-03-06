package com.example.music.song;

import com.example.music.rating.song.SongRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
}
