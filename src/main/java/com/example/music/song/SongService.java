package com.example.music.song;

import com.example.music.album.Album;
import com.example.music.album.AlbumRepository;
import com.example.music.artist.Artist;
import com.example.music.artist.ArtistRepository;
import com.example.music.relationships.album_song.SongAlbumRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;
    @Transactional
    public ResponseEntity<SongDTO> saveSong(SongDTO songDTO) {
        Song song = modelMapper.map(songDTO, Song.class);

        song = songRepository.save(song);

        if (songDTO.getAlbumId() != null){
            Album album = albumRepository.findById(songDTO.getAlbumId()).orElseThrow();
            album.addSong(song);
            song.setAlbum(album);
        }

        if (songDTO.getArtistsId() != null){
            for (Integer artistId: songDTO.getArtistsId()){
                Artist artist = artistRepository.findById(artistId).orElseThrow();
                artist.addSong(song);
                song.addWrittenBy(artist);
            }
        }


        songDTO = modelMapper.map(song, SongDTO.class);

        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    public ResponseEntity<Song> getSong(int id){
        Song song = songRepository.findById(id).orElseThrow();
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

}
