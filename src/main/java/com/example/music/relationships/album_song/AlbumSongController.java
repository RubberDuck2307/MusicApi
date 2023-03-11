package com.example.music.relationships.album_song;

import com.example.music.album.AlbumDTO;
import com.example.music.song.SongDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/album")
public class AlbumSongController {

    private final AlbumSongService service;

    @PostMapping("/song")
    public ResponseEntity<AlbumDTO> addSongToAlbum(@RequestBody SongAlbumRequest request){
        return service.addSongToAlbum(request);
    }

    @DeleteMapping("/song")
    public ResponseEntity<SongDTO> removeSongFromAlbum (@RequestBody SongAlbumRequest request){
        return service.removeSongFromAlbum(request);
    }

}
