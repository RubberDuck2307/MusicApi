package com.example.music.relationships.album_artist;

import com.example.music.album.Album;
import com.example.music.album.AlbumRepository;
import com.example.music.artist.Artist;
import com.example.music.artist.ArtistRepository;
import com.example.music.relationships.WrittenByRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class AlbumArtistService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;



    @Transactional
    public ResponseEntity<String> addAlbumToArtist(WrittenByRequest request) {
        Artist artist = artistRepository.findById(request.getArtistId()).orElseThrow();
        Album album = albumRepository.findById(request.getItemId()).orElseThrow();

        artist.addAlbum(album);
        album.addArtist(artist);


        return new ResponseEntity<>("Album has been added to author", HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<String> removeAlbumFromArtist(WrittenByRequest request) {
        Artist artist = artistRepository.findById(request.getArtistId()).orElseThrow();
        Album album = albumRepository.findById(request.getItemId()).orElseThrow();


        return new ResponseEntity<>("Album has been removed from author", HttpStatus.OK);
    }

    public ResponseEntity<List<Album>> getAllAlbumsOfArtist(int id) {
        Artist artist = artistRepository.findById(id).orElseThrow();
        List<Album> albums = new ArrayList<>();
        artist.getAlbums().forEach((album -> albums.add(album)));


        return new ResponseEntity<>(albums, HttpStatus.OK);
    }
}
