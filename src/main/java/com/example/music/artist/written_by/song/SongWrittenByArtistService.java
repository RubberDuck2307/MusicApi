package com.example.music.artist.written_by.song;

import com.example.music.artist.Artist;
import com.example.music.artist.ArtistRepository;
import com.example.music.artist.written_by.WrittenByRequest;
import com.example.music.song.Song;
import com.example.music.song.SongDTO;
import com.example.music.song.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongWrittenByArtistService {

    private final SongWrittenByAuthorRepository writtenByRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    public ResponseEntity<String> addSongToArtist(WrittenByRequest request){
        Song song = songRepository.findById(request.getItemId()).orElseThrow();
        Artist artist = artistRepository.findById(request.getArtistId()).orElseThrow();

        SongWrittenByArtistKey key = new SongWrittenByArtistKey(artist.getId(), song.getId());
        SongWrittenByArtist songWrittenByArtist = new SongWrittenByArtist(key, song, artist);

        song.addWrittenBy(songWrittenByArtist);
        artist.addSong(songWrittenByArtist);

        writtenByRepository.save(songWrittenByArtist);

        return new ResponseEntity<>("Song has been added to artist", HttpStatus.OK);
    }


    public ResponseEntity<String> removeSongFromArtist(WrittenByRequest request){
        Song song = songRepository.findById(request.getItemId()).orElseThrow();
        Artist artist = artistRepository.findById(request.getArtistId()).orElseThrow();

        SongWrittenByArtistKey key = new SongWrittenByArtistKey(artist.getId(), song.getId());

        SongWrittenByArtist songWrittenByArtist = writtenByRepository.findById(key).orElseThrow();

        song.removeWrittenBy(songWrittenByArtist);
        artist.removeSong(songWrittenByArtist);

        writtenByRepository.delete(songWrittenByArtist);

        return new ResponseEntity<>("Song has been removed from artist", HttpStatus.OK);
    }
    public ResponseEntity<List<SongDTO>> getAllSongsOfArtist(int id){

        Artist artist = artistRepository.findById(id).orElseThrow();
        ArrayList<SongDTO> songs = new ArrayList<>();
        artist.getSongsWritten().forEach(songWrittenByArtist -> songs.add(songWrittenByArtist.getSong().getDTO()));

        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
