package com.project.Spring_Project_Artists.service;

import com.project.Spring_Project_Artists.dto.ArtistDto;

import java.util.Set;

public interface ArtistService {

    Set<ArtistDto> findAll();

    ArtistDto findById(Long id);

    ArtistDto findByName(String name);

    ArtistDto findByLabelName(String labelName);

    ArtistDto save(ArtistDto artistDto);

    ArtistDto update(ArtistDto artistDto);

    void deleteById(Long id);
}
