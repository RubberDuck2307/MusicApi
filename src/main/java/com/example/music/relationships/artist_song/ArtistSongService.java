package com.example.music.relationships.artist_song;

import com.example.music.artist.Artist;
import com.example.music.artist.ArtistRepository;
import com.example.music.relationships.WrittenByRequest;
import com.example.music.song.Song;
import com.example.music.song.SongDTO;
import com.example.music.song.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistSongService {

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ResponseEntity<SongDTO> addSongToArtist(WrittenByRequest request){
        Artist artist = artistRepository.findById(request.getArtistId()).orElseThrow();
        Song song = songRepository.findById(request.getItemId()).orElseThrow();

        song.addWrittenBy(artist);
        artist.addSong(song);

        SongDTO songDTO = modelMapper.map(song,SongDTO.class);

        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<SongDTO> removeSongFromArtist(WrittenByRequest request){

        Artist artist = artistRepository.findById(request.getArtistId()).orElseThrow();
        Song song = songRepository.findById(request.getItemId()).orElseThrow();

        song.removeWrittenBy(artist);
        artist.removeSong(song);

        SongDTO songDTO = modelMapper.map(song,SongDTO.class);

        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

}
