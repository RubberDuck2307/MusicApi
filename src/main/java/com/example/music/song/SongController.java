package com.example.music.song;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/song")
public class SongController {

    private final SongService songService;
    @PostMapping("/")
    public ResponseEntity<String> saveSong(){
        System.out.println("hello");
        return songService.saveSong();

    }

}
