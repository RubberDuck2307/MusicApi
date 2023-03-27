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
    public ResponseEntity<SongDTO> getSong(@PathVariable int id){
        return songService.getSong(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SongDTO> modifySong(@PathVariable int id, @RequestBody SongDTO songDTO){
        songService.modifySong(id, songDTO);
        return songService.getSong(id);
    }
}
