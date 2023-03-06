package com.example.music.rating.song;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRatingRepository extends JpaRepository<SongRating, SongRatingKey> {

    boolean existsById(SongRatingKey songRatingKey);
}
