package com.project.Spring_Project_Artists.controller;

import com.project.Spring_Project_Artists.dto.SongDto;
import com.project.Spring_Project_Artists.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/songs")
public class SongController {

    private SongService songService;

    @Autowired
    public SongController(SongService songService){
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<Set<SongDto>> findAll(){
        return ResponseEntity.ok(songService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SongDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(songService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<SongDto> findByName(@PathVariable @Valid String name){
        return ResponseEntity.ok(songService.findByName(name));
    }


    @GetMapping(value = "/album/{findByAlbumName}")
    public ResponseEntity<SongDto> findByAlbumName(@PathVariable @Valid String albumName){
        return ResponseEntity.ok(songService.findByAlbumName(albumName));
    }

    @PostMapping(value = "/save/{songDto}")
    public ResponseEntity<SongDto> save(@RequestBody SongDto songDto){
        return ResponseEntity.ok(songService.save(songDto));
    }

    @PutMapping(value = "/update/{songDto}")
    public ResponseEntity<SongDto> update(@RequestBody SongDto songDto){
        return ResponseEntity.ok(songService.update(songDto));
    }

    @DeleteMapping(value = "{id}")
    public void deleteById(@PathVariable Long id){
        songService.deleteById(id);
    }

}
