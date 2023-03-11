package com.example.music.relationships.album_song;

import com.example.music.album.Album;
import com.example.music.album.AlbumDTO;
import com.example.music.album.AlbumRepository;
import com.example.music.exception.custom_exceptions.SongInAlbumException;
import com.example.music.song.Song;
import com.example.music.song.SongDTO;
import com.example.music.song.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class AlbumSongService {

    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ResponseEntity<AlbumDTO> addSongToAlbum(SongAlbumRequest request){
        Album album = albumRepository.findById(request.getAlbumId()).orElseThrow();
        Song song = songRepository.findById(request.getSongId()).orElseThrow();

        if (song.getAlbum() != null){
            throw new SongInAlbumException();

        }

        album.addSong(song);
        song.setAlbum(album);

        AlbumDTO albumDTO = modelMapper.map(album, AlbumDTO.class);

        return new ResponseEntity<>(albumDTO, HttpStatus.OK);

    }

    @Transactional
    public ResponseEntity<SongDTO> removeSongFromAlbum(SongAlbumRequest request){
        Album album = albumRepository.findById(request.getAlbumId()).orElseThrow();
        Song song = songRepository.findById(request.getSongId()).orElseThrow();

        if (song.getAlbum() == null){
            throw new RuntimeException();
        }

        album.removeSong(song);
        song.setAlbum(null);
        SongDTO songDTO = modelMapper.map(song, SongDTO.class);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }




}
