package com.project.Spring_Project_Artists.repository;

import com.project.Spring_Project_Artists.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findById(Long id);

    Optional<Artist> findByName(String name);

    Optional<Artist> findByLabelName(String labelName);

    void deleteById(Long id);

}
