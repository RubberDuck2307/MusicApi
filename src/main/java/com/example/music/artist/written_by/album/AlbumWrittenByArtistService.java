package com.example.music.artist.written_by.album;

import com.example.music.album.Album;
import com.example.music.album.AlbumDTO;
import com.example.music.album.AlbumRepository;
import com.example.music.artist.Artist;
import com.example.music.artist.ArtistRepository;
import com.example.music.artist.written_by.WrittenByRequest;
import com.example.music.song.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class AlbumWrittenByArtistService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    private final AlbumWrittenByArtistRepository writtenByRepository;

    @Transactional
    public ResponseEntity<String> addAlbumToArtist(WrittenByRequest request){
        Artist artist = artistRepository.findById(request.getArtistId()).orElseThrow();
        Album album = albumRepository.findById(request.getItemId()).orElseThrow();

        AlbumWrittenByArtistKey key = new AlbumWrittenByArtistKey(request.getArtistId(), request.getItemId());
        AlbumWrittenByArtist albumWrittenByArtist = new AlbumWrittenByArtist(key, album, artist);

        artist.addAlbum(albumWrittenByArtist);
        album.addWrittenBy(albumWrittenByArtist);

        writtenByRepository.save(albumWrittenByArtist);

        return new ResponseEntity<>("Album has been added to author", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> removeAlbumFromArtist(WrittenByRequest request){
        Artist artist = artistRepository.findById(request.getArtistId()).orElseThrow();
        Album album = albumRepository.findById(request.getItemId()).orElseThrow();

        AlbumWrittenByArtistKey key = new AlbumWrittenByArtistKey(request.getArtistId(), request.getItemId());

        writtenByRepository.deleteById(key);



        return new ResponseEntity<>("Album has been removed from author", HttpStatus.OK);
    }

    public ResponseEntity<List<AlbumDTO>> getAllAlbumsOfArtist(int id){
        Artist artist = artistRepository.findById(id).orElseThrow();
        List<AlbumWrittenByArtist> writtenBy = writtenByRepository.findAllByArtist(artist);
        List<AlbumDTO> albums = new ArrayList<>();
        writtenBy.forEach((record) -> albums.add(record.getAlbum().getDTO()));
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }
}
