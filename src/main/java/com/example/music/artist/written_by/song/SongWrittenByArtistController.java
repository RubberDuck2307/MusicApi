package com.example.music.artist.written_by.song;

import com.example.music.artist.written_by.WrittenByRequest;
import com.example.music.song.SongDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/artist/song")
public class SongWrittenByArtistController {

    private final SongWrittenByArtistService service;


    @PostMapping("/")
    public ResponseEntity<String> addSongToArtist(@RequestBody WrittenByRequest request){
        return service.addSongToArtist(request);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> removeSongFromArtist(@RequestBody WrittenByRequest request){
        return service.removeSongFromArtist(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SongDTO>> getAllSongsOfArtist(@PathVariable int id){
       return service.getAllSongsOfArtist(id);
    }
}
