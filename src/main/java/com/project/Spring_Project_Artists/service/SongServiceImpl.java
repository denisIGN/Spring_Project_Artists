package com.project.Spring_Project_Artists.service;

import com.project.Spring_Project_Artists.dto.SongDto;
import com.project.Spring_Project_Artists.exception.BadRequestException;
import com.project.Spring_Project_Artists.exception.DuplicateResourceException;
import com.project.Spring_Project_Artists.exception.ResourceNotFoundException;
import com.project.Spring_Project_Artists.model.Artist;
import com.project.Spring_Project_Artists.model.Playlist;
import com.project.Spring_Project_Artists.model.Song;
import com.project.Spring_Project_Artists.repository.SongRepository;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService{

    private SongRepository songRepository;
    private ArtistService artistService;
    private PlaylistService playlistService;
    private ModelMapper modelMapper;

    @Autowired
    public SongServiceImpl(SongRepository songRepository,
                           ModelMapper modelMapper,
                           ArtistService artistService,
                           PlaylistService playlistService){
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
        this.artistService = artistService;
        this.playlistService = playlistService;
    }

    @Override
    public Set<SongDto> findAll() {
        return songRepository.findAll()
                .stream().map(song -> modelMapper.map(song, SongDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public SongDto findById(@NonNull Long id) {
        Song maybeSong = songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find song with ID: " + id));
        return modelMapper.map(maybeSong, SongDto.class);
    }

    @Override
    public SongDto findByName(@NonNull String name) {
        Song maybeSong = songRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find song with name: " + name));
        return modelMapper.map(maybeSong, SongDto.class);
    }

    @Override
    public SongDto findByAlbumName(@NonNull String albumName) {
        Song maybeSong = songRepository.findByAlbumName(albumName)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find song with album name: " + albumName));
        return modelMapper.map(maybeSong, SongDto.class);
    }

    @Override
    public SongDto save(SongDto songDto) {

        try {
            songDto.setId(null);
            Song song = modelMapper.map(songDto, Song.class);
            String artistName = songDto.getArtistDto().getName();

            Artist artist = modelMapper.map(artistService.findByName(artistName), Artist.class);
            song.setArtist(artist);

            String playlistName = songDto.getPlaylistDto().getName();
            Playlist playlist = modelMapper.map(playlistService.findByName(playlistName), Playlist.class);
            song.setPlaylist(playlist);
            songRepository.save(song);
            return modelMapper.map(song, SongDto.class);
        }catch (DataIntegrityViolationException ex){
          throw new DuplicateResourceException("Can't save this object due to incomplete data.");
        }
    }

    @Override
    public SongDto update(SongDto songDto) {

        try {
            Song song = modelMapper.map(songDto, Song.class);
            Song updatedSong = songRepository.save(song);
            return modelMapper.map(updatedSong, SongDto.class);
        }catch (DataIntegrityViolationException ex){
         throw new BadRequestException("Can't update object with information: " + songDto);
        }
    }

    @Override
    public void deleteById(@NonNull Long id) {
        try {
            songRepository.deleteById(id);
        }catch (DataIntegrityViolationException ex){
         throw new ResourceNotFoundException("Can't find object with ID: " + id + " in order to delete it.");
        }
    }
}
