package com.TMC.WebFootballers.Controllers;

import com.TMC.WebFootballers.Services.FootballTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Teams")
public class FootballTeamsController {
    @Autowired
    private FootballTeamService service;

    @GetMapping("/search")
    public List<String> getRelevantTeamNames(@RequestParam String term){
        return service.GetRelevantTeamNames(term);
    }
}