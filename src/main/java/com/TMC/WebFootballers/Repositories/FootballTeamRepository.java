package com.TMC.WebFootballers.Repositories;

import com.TMC.WebFootballers.Entities.FootballTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface FootballTeamRepository extends JpaRepository<FootballTeam,Long> {
    Optional<FootballTeam> findByName(String name);
    boolean existsByName(String name);

    ArrayList<FootballTeam> findAllByNameContainsIgnoreCase(String input);
}
