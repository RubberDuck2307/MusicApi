package com.example.music.song;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/song")
public class SongController {

    private final SongService songService;
    @PostMapping("/")
    public ResponseEntity<String> saveSong(@RequestBody Song song){
        return songService.saveSong(song);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable int id){
        return songService.getSong(id);
    }

    @PostMapping("/album")
    public ResponseEntity<String> addSongToAlbum(@RequestBody AddSongToAlbumRequest request){
       return songService.addSongToAlbum(request);

    }

    @DeleteMapping("/album/{id}")
    public ResponseEntity<String> removeSongFromAlbum(@PathVariable int id){
        return songService.removeSongFromAlbum(id);
    }

}
