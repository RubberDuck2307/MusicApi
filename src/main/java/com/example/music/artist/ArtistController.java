package com.example.music.artist;

import com.example.music.exception.UserEmailTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artist")
@RequiredArgsConstructor

public class ArtistController {

    private final ArtistService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtist(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(service.getArtist(id));
        }
        catch (Exception exception){
            return ResponseEntity.badRequest().body("No artist with such ID");}

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifyArtist(@PathVariable Integer id, @RequestBody ArtistDTO artistDTO){
        try {
            service.modifyArtist(id,artistDTO);
            return ResponseEntity.ok("Record modified");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("No Artist with such ID");
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtist(@PathVariable Integer id){
        try {
            service.deleteArtist(id);
            return ResponseEntity.ok("Record deleted");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("No Artist with such ID");
        }

    }

    @PostMapping("/")
    public ResponseEntity<String> createArtist(@RequestBody Artist artist){
        service.createArtist(artist);
        return ResponseEntity.ok("ArtistCreated");

    }

    @GetMapping("/all")
    public ResponseEntity<List<Artist>> getAllArtist(){
        return ResponseEntity.ok(service.getAllArtists());
    }

}
