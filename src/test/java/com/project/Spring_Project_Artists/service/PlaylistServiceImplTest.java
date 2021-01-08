package com.project.Spring_Project_Artists.service;


import com.project.Spring_Project_Artists.dto.PlaylistDto;
import com.project.Spring_Project_Artists.exception.BadRequestException;
import com.project.Spring_Project_Artists.model.Artist;
import com.project.Spring_Project_Artists.model.Playlist;
import com.project.Spring_Project_Artists.repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaylistServiceImplTest {

    @Mock
    PlaylistRepository playlistRepository;

    PlaylistService playlistService;
    ModelMapper modelMapper;

    @BeforeEach
    public void setUp(){
        modelMapper = new ModelMapper();
        playlistService = new PlaylistServiceImpl(playlistRepository, modelMapper);
    }

    @Test
    public void saveSuccess(){
        Playlist playlist = new Playlist();
        playlist.setName("Test Playlist");

        when(playlistRepository.save(eq(playlist)))
                .thenReturn(playlist);

        PlaylistDto actualPlaylistDto = playlistService.save(modelMapper.map(playlist, PlaylistDto.class));

        assertThat(actualPlaylistDto, is(notNullValue()));
        assertEquals(actualPlaylistDto.getName(), "Test Playlist");
    }


}