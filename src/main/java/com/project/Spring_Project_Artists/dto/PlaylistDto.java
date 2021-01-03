package com.project.Spring_Project_Artists.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
public class PlaylistDto {

    private Long id;

    @NotEmpty
    @Size(min = 5, max = 15)
    private String name;

    private Set<SongDto> songs;
}
