package com.example.music.admin.song;

import com.example.music.song.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/admin/song")
public class SongAdminController {

    private final SongService service;


    @DeleteMapping("/{id}")
    private ResponseEntity<String> removeSong(@PathVariable int id){
        return service.removeSong(id);
    }

}
