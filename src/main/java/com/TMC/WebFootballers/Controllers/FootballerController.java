package com.TMC.WebFootballers.Controllers;

import com.TMC.WebFootballers.DTOs.FootballerCreationDTO;
import com.TMC.WebFootballers.DTOs.FootballerDTO;
import com.TMC.WebFootballers.Services.FootballerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Players")
public class FootballerController {
    @Autowired
    private FootballerService service;
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/Add")
    public ResponseEntity<HttpStatus> addFootballer(@Valid @ModelAttribute FootballerCreationDTO dto){
        service.AddFootballer(dto);
        template.convertAndSend("/topic", "update");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/Change")
    public ResponseEntity<HttpStatus> alterFootballer(@Valid @ModelAttribute FootballerDTO dto){
        service.AlterFootballer(dto);
        template.convertAndSend("/topic", "update");
        return ResponseEntity.ok().build();
    }
}
