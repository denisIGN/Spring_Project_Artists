package com.project.Spring_Project_Artists.service;

import com.project.Spring_Project_Artists.dto.PlaylistDto;
import com.project.Spring_Project_Artists.exception.BadRequestException;
import com.project.Spring_Project_Artists.exception.DuplicateResourceException;
import com.project.Spring_Project_Artists.exception.ResourceNotFoundException;
import com.project.Spring_Project_Artists.model.Playlist;
import com.project.Spring_Project_Artists.repository.PlaylistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlaylistServiceImpl implements PlaylistService{

    private PlaylistRepository playlistRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository,
                               ModelMapper modelMapper){
        this.playlistRepository = playlistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<PlaylistDto> findAll(){
        return playlistRepository.findAll()
                .stream().map(playlist -> modelMapper.map(playlist, PlaylistDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public PlaylistDto findByName(String name){
        Playlist maybePlaylist = playlistRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find playlist with name: " + name));
        return modelMapper.map(maybePlaylist, PlaylistDto.class);
    }

    @Override
    public PlaylistDto save(PlaylistDto playlistDto){

        try {
            Playlist playlist = modelMapper.map(playlistDto, Playlist.class);
            playlistRepository.save(playlist);
            return modelMapper.map(playlist, PlaylistDto.class);
        }catch (DataIntegrityViolationException ex){
         throw new DuplicateResourceException("Can't save object due to duplicate information.");
        }
    }

    @Override
    public PlaylistDto update(PlaylistDto playlistDto){

        try {
            Playlist playlist = modelMapper.map(playlistDto, Playlist.class);
            Playlist updatedPlaylist = playlistRepository.save(playlist);
            return modelMapper.map(updatedPlaylist, PlaylistDto.class);
        }catch (DataIntegrityViolationException ex){
         throw new BadRequestException("Can't update this object with with information: " + playlistDto);
        }
    }

    @Override
    public void deleteByName(String name) {

        try {
            playlistRepository.deleteByName(name);
        }catch (DataIntegrityViolationException ex){
         throw new ResourceNotFoundException("Can't find object with ID: " + name + " in order to delete it.");
        }
    }
}
