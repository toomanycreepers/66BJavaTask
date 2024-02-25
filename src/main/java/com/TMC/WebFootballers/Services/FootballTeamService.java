package com.TMC.WebFootballers.Services;

import com.TMC.WebFootballers.Entities.FootballTeam;
import com.TMC.WebFootballers.Repositories.FootballTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootballTeamService {
    @Autowired
    private FootballTeamRepository repo;

    public List<String> GetRelevantTeamNames(String searchTerm){
        if (searchTerm==null||searchTerm.isBlank()) {
            throw new IllegalArgumentException("Search term shouldn't be empty");
        }
        List<FootballTeam> teams = repo.findAllByNameContainsIgnoreCase(searchTerm);
        return teams.stream().map(FootballTeam::getName).toList();
    }
}
