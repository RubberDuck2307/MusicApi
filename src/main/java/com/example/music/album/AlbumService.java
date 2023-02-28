package com.example.music.album;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository repository;

    public void createAlbum(Album album){
        repository.save(album);
    }

}
