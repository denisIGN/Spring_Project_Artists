package com.project.Spring_Project_Artists;

import com.project.Spring_Project_Artists.dto.ArtistDto;
import com.project.Spring_Project_Artists.dto.PlaylistDto;
import com.project.Spring_Project_Artists.dto.SongDto;
import com.project.Spring_Project_Artists.model.GenreType;
import com.project.Spring_Project_Artists.service.ArtistService;
import com.project.Spring_Project_Artists.service.PlaylistService;
import com.project.Spring_Project_Artists.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class ApplicationRunner implements CommandLineRunner {

    private ArtistService artistService;
    private SongService songService;
    private PlaylistService playlistService;

    @Autowired
    public ApplicationRunner(ArtistService artistService,
                             SongService songService,
                             PlaylistService playlistService){
        this.artistService = artistService;
        this.songService = songService;
        this.playlistService = playlistService;
    }

    @Override
    public void run(String... args) throws Exception {
        //testing object creation and insertion
        PlaylistDto myPlaylist = new PlaylistDto();
        myPlaylist.setName("Test Playlist");
        playlistService.save(myPlaylist);

        PlaylistDto myPlaylist2 = new PlaylistDto();
        myPlaylist2.setName("Test Playlist 2");
        playlistService.save(myPlaylist2);

        ArtistDto testArtist = new ArtistDto();
        testArtist.setName("Test Artist");
        testArtist.setAboutInfo("Test information");
        testArtist.setGenreType(GenreType.POP);
        testArtist.setLabelName("Test Label");
        artistService.save(testArtist);

        ArtistDto testArtist2 = new ArtistDto();
        testArtist2.setName("Test Artist 2");
        testArtist2.setAboutInfo("Test information 2");
        testArtist2.setGenreType(GenreType.POP);
        testArtist2.setLabelName("Test Label 2");
        artistService.save(testArtist2);

        SongDto testSong = new SongDto();
        testSong.setName("Test Name");
        testSong.setAlbumName("Test Album");
        testSong.setYearPublished(1999);
        testSong.setArtistDto(testArtist);
        testSong.setPlaylistDto(myPlaylist);
        songService.save(testSong);

        SongDto testSong2 = new SongDto();
        testSong2.setName("Test Name 2");
        testSong2.setAlbumName("Test Album 2");
        testSong2.setYearPublished(2002);
        testSong2.setArtistDto(testArtist2);
        testSong2.setPlaylistDto(myPlaylist2);
        songService.save(testSong2);



    }
}
