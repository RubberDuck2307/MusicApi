package com.example.music.relationships.album_artist;

import com.example.music.album.Album;
import com.example.music.relationships.WrittenByRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/artist/album")
@RequiredArgsConstructor
public class AlbumArtistController {

    private final AlbumArtistService writtenByService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Album>> getAllAlbumsOfArtist(@PathVariable int id){
        return writtenByService.getAllAlbumsOfArtist(id);

    }
    @PostMapping("/")
    public ResponseEntity<String> addAlbumToArtist(@RequestBody WrittenByRequest request) {
        return writtenByService.addAlbumToArtist(request);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> removeAlbumFromArtist(@RequestBody WrittenByRequest request) {
        return writtenByService.removeAlbumFromArtist(request);
    }
}
