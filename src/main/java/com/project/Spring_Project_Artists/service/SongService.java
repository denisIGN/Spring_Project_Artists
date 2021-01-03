package com.project.Spring_Project_Artists.service;

import com.project.Spring_Project_Artists.dto.SongDto;

import java.util.Set;

public interface SongService {

    Set<SongDto> findAll();

    SongDto findById(Long id);

    SongDto findByName(String name);

    SongDto findByAlbumName(String albumName);

    SongDto save(SongDto songDto);

    SongDto update(SongDto songDto);

    void deleteById(Long id);

}
