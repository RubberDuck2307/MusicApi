package com.example.music.rating.album;

import com.example.music.album.Album;
import com.example.music.album.AlbumRepository;
import com.example.music.rating.RatingRequest;
import com.example.music.user.User;
import com.example.music.user.UserCredentials;
import com.example.music.user.UserCredentialsRepository;
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
public class AlbumRatingService {

    private final AlbumRatingRepository ratingRepository;
    private final AlbumRepository albumRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserRepository userRepository;

    public ResponseEntity<String> rateAlbum(Integer albumId, RatingRequest request) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials userCredentials = (UserCredentials) auth.getPrincipal();
        User user = userRepository.findById(userCredentials.getId()).orElseThrow();
        Album album = albumRepository.findById(albumId).orElseThrow();
        AlbumRatingKey albumRatingKey = new AlbumRatingKey(user.getId(), albumId);
        System.out.println(request.getRating());
        AlbumRating albumRating = AlbumRating.builder()
                .album(album)
                .user(user)
                .review(request.getReview())
                .rating(request.getRating())
                .id(albumRatingKey)
                .build();
        album.addRating(albumRating);
        user.addAlbumRating(albumRating);

        ratingRepository.save(albumRating);

        return new ResponseEntity<>("Album rating has been created", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAlbumRating(Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials userCredentials = (UserCredentials) auth.getPrincipal();
        User user = userRepository.findById(userCredentials.getId()).orElseThrow();
        AlbumRatingKey albumRatingKey = new AlbumRatingKey(user.getId(), id);
        if (ratingRepository.existsAlbumRatingById(albumRatingKey)){
            ratingRepository.deleteById(albumRatingKey);
            return new ResponseEntity<>("Album rating has been deleted", HttpStatus.OK);
        }
        else throw new NoSuchElementException();
    }


}
