package com.example.music.song;

import com.example.music.relationships.album_song.SongAlbumRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/song")
public class SongController {

    private final SongService songService;
    @PostMapping("/")
    public ResponseEntity<SongDTO> saveSong(@RequestBody SongDTO songDTO){
        return songService.saveSong(songDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable int id){
        return songService.getSong(id);
    }


}
