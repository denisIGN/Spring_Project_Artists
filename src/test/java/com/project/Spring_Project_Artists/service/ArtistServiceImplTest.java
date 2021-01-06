package com.project.Spring_Project_Artists.service;

import com.project.Spring_Project_Artists.dto.ArtistDto;
import com.project.Spring_Project_Artists.exception.DuplicateResourceException;
import com.project.Spring_Project_Artists.model.Artist;
import com.project.Spring_Project_Artists.repository.ArtistRepository;
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
import static org.mockito.Mockito.*;


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

    @Test
    public void duplicateThrowsExSuccess(){

        Artist artist = new Artist();
        artist.setName("Test name");

        when(artistRepository.save(eq(artist)))
                .thenThrow(DataIntegrityViolationException.class);

        ArtistDto artistDto = modelMapper.map(artist, ArtistDto.class);
        artistDto.setName("Test name");
        assertThrows(DuplicateResourceException.class, () -> artistServiceImpl.save(artistDto));
    }

    @Test
    public void deleteByIdSuccess(){
        Long id = 1L;
        artistServiceImpl.deleteById(id);

        verify(artistRepository, times(1)).deleteById(id);
    }

    @Test
    public void deleteByIdWhenNullProvided(){
            assertThrows(NullPointerException.class, () -> artistServiceImpl.deleteById(null));
    }

}