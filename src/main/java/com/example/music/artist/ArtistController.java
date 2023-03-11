package com.example.music.artist;

import com.example.music.song.SongDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artist")
@RequiredArgsConstructor

public class ArtistController {

    private final ArtistService service;

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtist(@PathVariable Integer id) {

        return service.getArtist(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> modifyArtist(@PathVariable Integer id, @RequestBody ArtistDTO artistDTO) {

        return service.modifyArtist(id, artistDTO);

    }



    @PostMapping("/")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artist) {
        return service.createArtist(artist);


    }

    @GetMapping("/")
    public ResponseEntity<List<ArtistDTO>> getAllArtist() {
        return service.getAllArtists();
    }


}
