package com.example.music.rating.album;

import com.example.music.album.Album;
import com.example.music.album.AlbumRepository;
import com.example.music.user.User;
import com.example.music.user.UserCredentials;
import com.example.music.user.UserCredentialsRepository;
import com.example.music.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumRatingService {

    private final AlbumRatingRepository ratingRepository;
    private final AlbumRepository albumRepository;
    private final UserCredentialsRepository userCredentialsRepository;
    private final UserRepository userRepository;
    public void rateAlbum(Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials userCredentials = (UserCredentials) auth.getPrincipal();
        User user = userRepository.findById(1).orElseThrow();
        Album album = albumRepository.findById(id).orElseThrow();
        System.out.println(album);
        System.out.println(user);

        ratingRepository.save(new AlbumRating(album, user, 10));
        System.out.println(album.getRatings());
    }



}
