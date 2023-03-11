package com.example.music.relationships.artist_song;

import com.example.music.relationships.WrittenByRequest;
import com.example.music.song.SongDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ArtistSongController {
    private final ArtistSongService service;
    @PostMapping("/artist/song")
    public ResponseEntity<SongDTO> addSongToArtist(@RequestBody WrittenByRequest request){

        return service.addSongToArtist(request);
    }

    @DeleteMapping ("/artist/song")
    public ResponseEntity<SongDTO> removeSongFromArtist(@RequestBody WrittenByRequest request){

        return service.removeSongFromArtist(request);
    }



}
