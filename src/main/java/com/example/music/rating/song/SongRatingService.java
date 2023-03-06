package com.example.music.rating.song;

import com.example.music.rating.RatingRequest;
import com.example.music.song.Song;
import com.example.music.song.SongRepository;
import com.example.music.user.User;
import com.example.music.user.UserCredentials;
import com.example.music.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SongRatingService {

    private final SongRatingRepository songRatingRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public ResponseEntity<String> rateSong(int id, RatingRequest request){
        System.out.println("rating");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials userCredentials = (UserCredentials) auth.getPrincipal();

        User user = userRepository.findById(userCredentials.getId()).orElseThrow();
        Song song = songRepository.findById(id).orElseThrow();

        SongRatingKey key = new SongRatingKey(user.getId(), id);
        SongRating songRating = new SongRating(key, user, song,request.getRating(), request.getReview());

        user.addSongRating(songRating);
        song.addRating(songRating);

        songRatingRepository.save(songRating);

        return new ResponseEntity<>("Song has been rated", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteSongRating(int id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials userCredentials = (UserCredentials) auth.getPrincipal();

        User user = userRepository.findById(userCredentials.getId()).orElseThrow();
        SongRatingKey key = new SongRatingKey(user.getId(), id);

        if (songRatingRepository.existsById(key)){
            songRatingRepository.deleteById(key);
            return new ResponseEntity<>("Song rating has been removed", HttpStatus.OK);
        }
        else throw new NoSuchElementException();
    }
}
