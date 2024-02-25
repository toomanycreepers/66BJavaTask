package com.TMC.WebFootballers.Services;

import com.TMC.WebFootballers.DTOs.FootballerCreationDTO;
import com.TMC.WebFootballers.DTOs.FootballerDTO;
import com.TMC.WebFootballers.Entities.FootballTeam;
import com.TMC.WebFootballers.Entities.Footballer;
import com.TMC.WebFootballers.Repositories.FootballTeamRepository;
import com.TMC.WebFootballers.Repositories.FootballerRepository;
import com.TMC.WebFootballers.Utility.Country;
import com.TMC.WebFootballers.Utility.CountryDBConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FootballerService {
    @Autowired
    private FootballerRepository footballerRepo;
    @Autowired
    private FootballTeamRepository teamRepo;
    @Autowired
    private CountryDBConverter converter;

    public List<FootballerDTO> GetAllFootballers() {
        return TransformListToDTOList(footballerRepo.findAll());
    }

    public void AddFootballer(FootballerCreationDTO dto) {
        AddTeam(dto.getTeam());
        var footballer = ConstructFromCreationDTO(dto);
        footballerRepo.save(footballer);
    }

    public void AlterFootballer(FootballerDTO dto) {
        try
        {
            RewriteFootballerData(dto);
        }
        catch (NoSuchElementException e)
        {
            return;
        }
    }

    private void AddTeam(String name){
        if (!teamRepo.existsByName(name))
            teamRepo.save(new FootballTeam(name));
    }

    private Footballer ConstructFromCreationDTO(FootballerCreationDTO dto) {
        Date dob = Date.valueOf(dto.getDob());
        FootballTeam team = teamRepo.findByName(dto.getTeam()).get();
        Country country = converter.convertToEntityAttribute(dto.getCountry());
        return  new Footballer(
                dto.getFirstName(),
                dto.getSurname(),
                dto.getIsMale(),
                dob,
                team,
                country
        );
    }

    private List<FootballerDTO> TransformListToDTOList(List<Footballer> footballerList) {
        var dtos = new ArrayList<FootballerDTO>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Footballer footballer : footballerList) {
            dtos.add(new FootballerDTO(footballer.getId(),
                    footballer.getFirstName(),
                    footballer.getSurname(),
                    footballer.getIsMale(),
                    sdf.format( footballer.getDateOfBirth()),
                    footballer.getTeam().getName(),
                    footballer.getCountry().getId()));
        }
        return dtos;
    }

    private void RewriteFootballerData (FootballerDTO dto) {
        var footballer = footballerRepo.findById(dto.getId()).orElse(null);
        if (footballer==null)
            throw new NoSuchElementException();
        footballer.setFirstName(dto.getFirstName());
        footballer.setSurname(dto.getSurname());
        footballer.setIsMale(dto.getIsMale());
        footballer.setDateOfBirth(Date.valueOf(dto.getDob()));
        footballer.setTeam(teamRepo.findByName(dto.getTeam()).get());
        footballer.setCountry(converter.convertToEntityAttribute( dto.getCountry()));
        footballerRepo.save(footballer);
    }
}