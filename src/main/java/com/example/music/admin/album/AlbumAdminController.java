package com.example.music.admin.album;

import com.example.music.album.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/album")
@RequiredArgsConstructor
public class AlbumAdminController {

    private final AlbumService albumService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeAlbum(@PathVariable int id){
        return albumService.removeAlbum(id);
    }



}
