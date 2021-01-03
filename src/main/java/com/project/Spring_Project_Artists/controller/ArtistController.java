package com.project.Spring_Project_Artists.controller;

import com.project.Spring_Project_Artists.dto.ArtistDto;
import com.project.Spring_Project_Artists.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/artists")
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService){
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<Set<ArtistDto>> findAll(){
        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArtistDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(artistService.findById(id));
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<ArtistDto> findByName(@PathVariable @Valid String name){
        return ResponseEntity.ok(artistService.findByName(name));
    }

    @GetMapping(value = "/label/{labelName}")
    public ResponseEntity<ArtistDto> findByLabelName(@PathVariable @Valid String labelName){
        return ResponseEntity.ok(artistService.findByLabelName(labelName));
    }

    @PostMapping(value = "/save/{artistDto}")
    public ResponseEntity<ArtistDto> save(@RequestBody ArtistDto artistDto){
        return ResponseEntity.ok(artistService.save(artistDto));
    }

    @PutMapping(value = "/update/{artistDto}")
    public ResponseEntity<ArtistDto> update(@RequestBody ArtistDto artistDto){
        return ResponseEntity.ok(artistService.update(artistDto));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id){
        artistService.deleteById(id);
    }


}
