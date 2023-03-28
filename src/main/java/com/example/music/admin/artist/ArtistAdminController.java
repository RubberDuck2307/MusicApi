package com.example.music.admin.artist;

import com.example.music.artist.Artist;
import com.example.music.artist.ArtistController;
import com.example.music.artist.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/admin/artist")
public class ArtistAdminController {

    final private ArtistService artistService;
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeArtist(@PathVariable int id){
       return artistService.removeArtist(id);
    }


}
