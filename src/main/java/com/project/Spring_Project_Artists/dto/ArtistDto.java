package com.project.Spring_Project_Artists.dto;

import com.project.Spring_Project_Artists.model.GenreType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class ArtistDto {

    private Long id;

    @NotEmpty
    @Size(min = 5)
    private String name;

    @NotEmpty
    private String labelName;

    @Size(min = 40, max = 3000)
    private String aboutInfo;

    private GenreType genreType;

    private Set<SongDto> songs;
}
