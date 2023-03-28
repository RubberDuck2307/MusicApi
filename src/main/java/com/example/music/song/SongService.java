package com.example.music.song;

import com.example.music.album.Album;
import com.example.music.album.AlbumRepository;
import com.example.music.artist.Artist;
import com.example.music.artist.ArtistRepository;
import com.example.music.artist.ArtistService;
import com.example.music.relationships.album_song.SongAlbumRequest;
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
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;
    private final ArtistService artistService;

    @Transactional
    public ResponseEntity<SongDTO> saveSong(SongDTO songDTO) {
        Song song = modelMapper.map(songDTO, Song.class);

        song = songRepository.save(song);

        if (songDTO.getAlbumId() != null) {
            Album album = albumRepository.findById(songDTO.getAlbumId()).orElseThrow();
            album.addSong(song);
            song.setAlbum(album);
        }

        if (songDTO.getArtistsId() != null) {
            for (Integer artistId : songDTO.getArtistsId()) {
                Artist artist = artistRepository.findById(artistId).orElseThrow();
                artist.addSong(song);
                song.addWrittenBy(artist);
            }
        }


        songDTO = modelMapper.map(song, SongDTO.class);

        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    public ResponseEntity<SongDTO> getSong(int id) {
        Song song = songRepository.findById(id).orElseThrow();
        SongDTO songDTO = modelMapper.map(song, SongDTO.class);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<SongDTO> modifySong(int id, SongDTO songDTO) {
        Song song = songRepository.findById(id).orElseThrow();

        if (songDTO.getTitle() != null && songDTO.getTitle().length() > 0) {
            song.setTitle(songDTO.getTitle());
        }
        if (songDTO.getLyrics() != null && songDTO.getLyrics().length() > 0) {
            song.setLyrics(songDTO.getLyrics());
        }
        if (songDTO.getLength() != null) {
            song.setLength(songDTO.getLength());
        }
        if (songDTO.getArtistsId() != null) {
            Set<Artist> artists = artistService.getArtistsByIds(songDTO.getArtistsId());
            for (Artist artist : artistService.getRemovedArtists(song.getArtists(), artists)) {
                artist.removeSong(song);
                song.removeWrittenBy(artist);
            }
            for (Artist artist : artistService.getAddedArtists(song.getArtists(), artists)) {
                artist.addSong(song);
                song.addWrittenBy(artist);
            }
            song.setArtists(artists);
        }
        songDTO = modelMapper.map(song, SongDTO.class);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    public ResponseEntity<String> removeSong(int id){
        songRepository.deleteById(id);
        return new ResponseEntity<>("Song has been successfully remove", HttpStatus.OK);
    }
}


