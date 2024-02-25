package com.TMC.WebFootballers.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "FootballTeams")
@NoArgsConstructor
@Getter
@RequiredArgsConstructor
public class FootballTeam {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TeamName")
    @NonNull
    private String name;
}
