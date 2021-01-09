package com.project.Spring_Project_Artists.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Spring_Project_Artists.dto.ArtistDto;
import com.project.Spring_Project_Artists.service.ArtistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ArtistController.class)
class ArtistControllerTest {

    @MockBean
    private ArtistService artistService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void findByIdSuccess() throws Exception{
        ArtistDto artistDto = new ArtistDto();
        artistDto.setId(1L);
        artistDto.setName("Test Name");

        when(artistService.findById(1L))
                .thenReturn(artistDto);
        mvc.perform(get("/artists/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Test Name")));

    }

    @Test
    void saveSuccess() throws Exception{
        ArtistDto artistDto = new ArtistDto();
        artistDto.setName("Test Name");

        String artistToJson = objectMapper.writeValueAsString(artistDto);

        when(artistService.save(artistDto))
                .thenReturn(artistDto);

        mvc.perform(post("/artists/save/")
                .content(artistToJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Test Name")));

    }
}