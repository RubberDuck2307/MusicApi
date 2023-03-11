package com.example.music.album;


import com.example.music.artist.Artist;
import com.example.music.artist.ArtistRepository;
import com.example.music.artist.ArtistService;
import com.example.music.song.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final ArtistRepository artistRepository;

    private final SongRepository songRepository;

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    private final ArtistService artistService;

    @Transactional
    public ResponseEntity<AlbumDTO> createAlbum(AlbumDTO albumDTO) {
        System.out.println(albumDTO);
        Album album = modelMapper.map(albumDTO, Album.class);


        album = albumRepository.save(album);

        if (albumDTO.getArtistsId() != null) {
            Set<Artist> artists = artistService.getArtistsByIds(albumDTO.getArtistsId());
            for (Artist artist : artists) {
                artist.addAlbum(album);
            }
            album.setArtists(artists);
        }

        albumDTO = modelMapper.map(album, AlbumDTO.class);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<AlbumDTO> changeAlbum(AlbumDTO albumDTO, int id){
        Album album = albumRepository.findById(id).orElseThrow();
        if (albumDTO.getTitle() != null){
            album.setTitle(albumDTO.getTitle());
        }
        if (albumDTO.getDateOfRelease() != null){
            album.setDateOfRelease(albumDTO.getDateOfRelease());
        }

        if (albumDTO.getArtistsId() != null) {
            Set<Artist> artists = artistService.getArtistsByIds(albumDTO.getArtistsId());
            Set<Artist> removedArtists = new HashSet<>();
            Set<Artist> addedArtists = new HashSet<>();

            for (Artist artist: artists){
                if (!album.getArtists().contains(artist)) addedArtists.add(artist);

            }

            for (Artist artist: album.getArtists()){
                if (!artists.contains(artist)) removedArtists.add(artist);

            }

            for (Artist artist: removedArtists) artist.removeAlbum(album);

            for (Artist artist: addedArtists) artist.addAlbum(album);

            album.setArtists(artists);
        }


        albumDTO = modelMapper.map(album, AlbumDTO.class);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }

    public ResponseEntity<AlbumDTO> getAlbum(int id) {
        Album album = albumRepository.findById(id).orElseThrow();
        AlbumDTO albumDTO = modelMapper.map(album, AlbumDTO.class);
        return new ResponseEntity<>(albumDTO, HttpStatus.OK);
    }



}
