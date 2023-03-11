package com.example.music.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Integer> {

    public Set<Artist> findAllByIdIsIn(Set<Integer> ids);
}
