package com.example.music.album;

import com.example.music.user.User;
import com.example.music.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/album")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService service;
    private final UserRepository repository;

    @PostMapping("/")
    public void createAlbum(@RequestBody Album album){
        service.createAlbum(album);
    }

    @PostMapping("/user")
    public void createUser(@RequestBody User user){
        repository.save(user);
    }

}
