package com.project.Spring_Project_Artists.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String labelName;

    @Column(nullable = false)
    private String aboutInfo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreType genreType;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private Set<Song> songs;

}
