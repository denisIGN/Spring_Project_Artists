package com.project.Spring_Project_Artists.service;

import com.project.Spring_Project_Artists.dto.ArtistDto;
import com.project.Spring_Project_Artists.model.Artist;
import com.project.Spring_Project_Artists.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ArtistServiceImplTest {

    @Mock
    private ArtistRepository artistRepository;

    private ModelMapper modelMapper;
    private ArtistServiceImpl artistServiceImpl;


    @BeforeEach
    public void setUp(){
        modelMapper = new ModelMapper();
        artistServiceImpl = new ArtistServiceImpl(artistRepository, modelMapper);
    }


    @Test
    public void saveSuccess(){
        Artist artist = new Artist();
        artist.setName("Test name");
        artist.setLabelName("Test label name");

        when(artistRepository.save(eq(artist)))
                .thenReturn(artist);

        ArtistDto actualArtistDto = artistServiceImpl.save(modelMapper.map(artist, ArtistDto.class));

        assertThat(actualArtistDto, is(notNullValue()));
        assertEquals(actualArtistDto.getName(), "Test name");
        assertEquals(actualArtistDto.getLabelName(), "Test label name");

    }

}