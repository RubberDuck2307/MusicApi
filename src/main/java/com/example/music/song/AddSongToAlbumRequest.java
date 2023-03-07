package com.example.music.song;

import lombok.Data;

@Data
public class AddSongToAlbumRequest {

    private int songId;
    private int albumId;
}
