package com.example.music.artist;

import com.example.music.album.Album;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;
    private final ModelMapper modelMapper;

    public ResponseEntity<ArtistDTO> getArtist(Integer id) {

        Artist artist = repository.findById(id).orElseThrow();
        ArtistDTO artistDTO = modelMapper.map(artist, ArtistDTO.class);
        return  new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    public ResponseEntity<ArtistDTO> createArtist(ArtistDTO artistDTO) {
        Artist artist = modelMapper.map(artistDTO, Artist.class);
        artist = repository.save(artist);

        artistDTO = modelMapper.map(artist, ArtistDTO.class);
        return new ResponseEntity<>(artistDTO, HttpStatus.OK);
    }

    public ResponseEntity<List<ArtistDTO>> getAllArtists() {

        List<Artist> artists = repository.findAll();
        List<ArtistDTO> artistsDTO = new ArrayList<>();

        for (Artist artist: artists) artistsDTO.add(modelMapper.map(artist, ArtistDTO.class));


        return new ResponseEntity<>(artistsDTO, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<ArtistDTO> modifyArtist(Integer id, ArtistDTO artistDTO) {
        Artist currentArtist = repository.findById(id).orElseThrow();

        if (artistDTO.getDob() != null) currentArtist.setDob(artistDTO.getDob());

        if (artistDTO.getLastName() != null && artistDTO.getLastName().length() > 0)
            currentArtist.setLastName(artistDTO.getLastName());

        if (artistDTO.getFirstName() != null && artistDTO.getFirstName().length() > 0)
            currentArtist.setFirstName(artistDTO.getFirstName());

        if (artistDTO.getNickname() != null && artistDTO.getNickname().length() > 0)
            currentArtist.setNickname(artistDTO.getNickname());

        artistDTO = modelMapper.map(currentArtist, ArtistDTO.class);

        return  ResponseEntity.ok(artistDTO);
    }


    public Set<Artist> getArtistsByIds(Set<Integer> ids){
        return repository.findAllByIdIsIn(ids);
    }

}
