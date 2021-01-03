package com.project.Spring_Project_Artists.repository;

import com.project.Spring_Project_Artists.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    Optional<Playlist> findByName(String name);

    void deleteByName(String name);

}
