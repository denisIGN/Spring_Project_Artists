package com.project.Spring_Project_Artists.dto;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class SongDto {

    private Long id;

    @NotEmpty
    @Size(min = 5, max = 30, message = "Song name must be between 5 and 30 characters long.")
    private String name;

    @NotEmpty
    @Size(max = 15, message = "Album name must not exceed 15 characters.")
    private String albumName;

    @Min(value = 1900)
    @Max(value = 2050)
    private int yearPublished;

    private ArtistDto artistDto;

    private PlaylistDto playlistDto;

}
