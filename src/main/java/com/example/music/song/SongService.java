package com.example.music.song;

import com.example.music.album.Album;
import com.example.music.album.AlbumRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public ResponseEntity<String> saveSong(Song song) {
        songRepository.save(song);
        return new ResponseEntity<>("Song has been successfully created", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> addSongToAlbum(AddSongToAlbumRequest request) {
        Song song = songRepository.findById(request.getSongId()).orElseThrow();
        Album album = albumRepository.findById(request.getAlbumId()).orElseThrow();

        song.setAlbum(album);
        album.addSong(song);
        return new ResponseEntity<>("Song has been added to the album", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> removeSongFromAlbum(int id) {
        Song song = songRepository.findById(id).orElseThrow();
        Integer albumId = song.getAlbum().getId();
        if (albumId != null) {
            song.setAlbum(null);
            Album album = albumRepository.findById(albumId).orElseThrow();
            album.removeSong(song);

            return new ResponseEntity<>("Song has been removed from an album", HttpStatus.OK);
        }
        else return new ResponseEntity<>("Song is not in an album", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Song> getSong(int id){
        Song song = songRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

}
