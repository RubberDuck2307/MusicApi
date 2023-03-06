package com.example.music.song;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;


    public ResponseEntity<String> saveSong(){
        songRepository.save(new Song());
        return new ResponseEntity<>("Song has been successfully created", HttpStatus.OK);
    }

}
