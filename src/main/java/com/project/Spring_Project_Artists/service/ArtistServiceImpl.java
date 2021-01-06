package com.project.Spring_Project_Artists.service;

import com.project.Spring_Project_Artists.dto.ArtistDto;
import com.project.Spring_Project_Artists.exception.BadRequestException;
import com.project.Spring_Project_Artists.exception.DuplicateResourceException;
import com.project.Spring_Project_Artists.exception.ResourceNotFoundException;
import com.project.Spring_Project_Artists.model.Artist;
import com.project.Spring_Project_Artists.repository.ArtistRepository;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService{

    private ArtistRepository artistRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository,
                             ModelMapper modelMapper){
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<ArtistDto> findAll(){
        return artistRepository.findAll()
                .stream().map(artist -> modelMapper.map(artist, ArtistDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public ArtistDto findById(@NonNull Long id){
        Artist maybeArtist = artistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find artist with ID: " + id));
        return modelMapper.map(maybeArtist, ArtistDto.class);
    }

    @Override
    public ArtistDto findByName(@NonNull String name){
        Artist maybeArtist = artistRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find artist with name: " + name));
        return modelMapper.map(maybeArtist, ArtistDto.class);
    }

    @Override
    public ArtistDto findByLabelName(@NonNull String labelName){
        Artist maybeArtist = artistRepository.findByLabelName(labelName)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find artist under the label name: " + labelName));
        return modelMapper.map(maybeArtist, ArtistDto.class);
    }

    @Override
    public ArtistDto save(@NonNull ArtistDto artistDto){

        try {
            artistDto.setId(null);
            Artist artist = modelMapper.map(artistDto, Artist.class);
            artistRepository.save(artist);
            return modelMapper.map(artist, ArtistDto.class);
        }catch (DataIntegrityViolationException ex){
          throw new DuplicateResourceException("Cannot save this object due to duplicate information.");
        }
    }

    @Override
    public ArtistDto update(@NonNull ArtistDto artistDto){

        try {
            Artist artist = modelMapper.map(artistDto, Artist.class);
            Artist updatedArtist = artistRepository.save(artist);
            return modelMapper.map(updatedArtist, ArtistDto.class);
        } catch (DataIntegrityViolationException ex){
         throw new BadRequestException("Can't update object with information: " + artistDto);
        }
    }

    @Override
    public void deleteById(@NonNull Long id){

        try {
            artistRepository.deleteById(id);
        }catch (DataIntegrityViolationException ex){
         throw new ResourceNotFoundException("Can't find artist with ID: " + id + " in order to delete it.");
        }
    }
}
