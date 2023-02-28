package com.example.music.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;


    public Artist getArtist(Integer id) {

        return repository.findById(id).orElseThrow();
    }

    public void createArtist(Artist artist) {
        repository.save(artist);
    }

    public List<Artist> getAllArtists() {
        return repository.findAll();
    }

    @Transactional
    public void modifyArtist(Integer id, ArtistDTO artist) {
        Artist currentArtist = repository.findById(id).orElseThrow();
        if (artist.getDob() != null) currentArtist.setDob(artist.getDob());
        if (artist.getLastName() != null && artist.getLastName().length() > 0)
            currentArtist.setLastName(artist.getLastName());
        if (artist.getFirstName() != null && artist.getFirstName().length() > 0)
            currentArtist.setFirstName(artist.getFirstName());

    }

    public void deleteArtist(Integer id) throws Exception {
        if (repository.findById(id).isPresent())
            repository.deleteById(id);
        else throw new Exception();
    }
}
