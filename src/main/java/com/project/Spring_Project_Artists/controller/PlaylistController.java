package com.project.Spring_Project_Artists.controller;

import com.project.Spring_Project_Artists.dto.PlaylistDto;
import com.project.Spring_Project_Artists.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/playlists")
public class PlaylistController {

    private PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<Set<PlaylistDto>> findAll(){
        return ResponseEntity.ok(playlistService.findAll());
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<PlaylistDto> findByName(@PathVariable @Valid String name){
        return ResponseEntity.ok(playlistService.findByName(name));
    }

    @PostMapping(value = "/save/")
    public ResponseEntity<PlaylistDto> save(@RequestBody PlaylistDto playlistDto){
        return ResponseEntity.ok(playlistService.save(playlistDto));
    }

    @PutMapping(value = "/update/")
    public ResponseEntity<PlaylistDto> update(@RequestBody PlaylistDto playlistDto){
        return ResponseEntity.ok(playlistService.update(playlistDto));
    }

    @DeleteMapping(value = "/{name}")
    public void deleteByName(@PathVariable String name){
        playlistService.deleteByName(name);
    }

}
