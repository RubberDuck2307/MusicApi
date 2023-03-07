package com.example.music.artist.written_by.album;

import com.example.music.album.Album;
import com.example.music.album.AlbumDTO;
import com.example.music.artist.written_by.WrittenByRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Controller
@RequestMapping("api/v1/artist/album")
@RequiredArgsConstructor
public class AlbumWrittenByArtistController {

    private final AlbumWrittenByArtistService writtenByService;

    @GetMapping("/{id}")
    public ResponseEntity<List<AlbumDTO>> getAllAlbumsOfArtist(@PathVariable int id){
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
