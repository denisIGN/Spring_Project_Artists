package com.project.Spring_Project_Artists.repository;

import com.project.Spring_Project_Artists.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Optional<Song> findById(Long id);

    Optional<Song> findByName(String name);

    Optional<Song> findByAlbumName(String albumName);

    void deleteById(Long id);
}
