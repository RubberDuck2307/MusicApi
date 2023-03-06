package com.example.music.rating.album;

import com.example.music.rating.RatingRequest;
import com.example.music.rating.album.AlbumRatingService;
import com.example.music.rating.song.SongRatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/rating/album")
@RequiredArgsConstructor
public class AlbumRatingController {

    private final AlbumRatingService albumRatingService;
    private final SongRatingService songRatingService;


    @PostMapping("/{id}")
    public ResponseEntity<String> rateAlbum(@PathVariable Integer id, @RequestBody @Valid RatingRequest request) {
       return albumRatingService.rateAlbum(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbumRating(@PathVariable Integer id){
        return albumRatingService.deleteAlbumRating(id);
    }
}


