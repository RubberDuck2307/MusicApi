package com.example.music.rating.song;

import com.example.music.rating.RatingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/rating/song")
public class SongRatingController {

    private final SongRatingService songRatingService;
    @PostMapping("/{id}")
    public ResponseEntity<String> rateSong(@PathVariable Integer id, @RequestBody @Valid RatingRequest request) {
        return songRatingService.rateSong(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSongRating(@PathVariable Integer id){
        return songRatingService.deleteSongRating(id);
    }

}
