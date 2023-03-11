package com.example.music.album;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/album")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService service;

    @PostMapping("/")
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO album){
        return service.createAlbum(album);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbum(@PathVariable int id){
       return service.getAlbum(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> changeAlbum(@PathVariable int id, @RequestBody AlbumDTO albumDTO){
        return service.changeAlbum(albumDTO, id);
    }

}
