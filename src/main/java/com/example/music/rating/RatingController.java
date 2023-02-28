package com.example.music.rating;

import com.example.music.rating.album.AlbumRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final AlbumRatingService service;
@GetMapping("/album/{id}")
    public void rateAlbum(@PathVariable Integer id){
    service.rateAlbum(id);
}

}
