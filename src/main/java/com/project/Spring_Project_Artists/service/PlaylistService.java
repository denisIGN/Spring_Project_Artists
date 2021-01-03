package com.project.Spring_Project_Artists.service;

import com.project.Spring_Project_Artists.dto.PlaylistDto;

import java.util.Set;

public interface PlaylistService {

    Set<PlaylistDto> findAll();

    PlaylistDto findByName(String name);

    PlaylistDto save(PlaylistDto playlistDto);

    PlaylistDto update(PlaylistDto playlistDto);

    void deleteByName(String name);

}
