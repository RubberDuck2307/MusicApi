package com.example.music.rating.album;

import com.example.music.rating.album.AlbumRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRatingRepository extends JpaRepository<AlbumRating, AlbumRatingKey> {
    Boolean existsAlbumRatingById(AlbumRatingKey albumRatingKey);
}
