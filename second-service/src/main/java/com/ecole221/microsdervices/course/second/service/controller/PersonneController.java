package com.ecole221.microsdervices.course.second.service.controller;

import com.ecole221.microsdervices.course.common.service.dto.AgeDTO;
import com.ecole221.microsdervices.course.second.service.service.AgeService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController

public class PersonneController {
    private final AgeService ageService;

    private static final Logger LOG = LogManager.getLogger(PersonneController.class);

    public PersonneController(AgeService ageService) {
        this.ageService = ageService;
    }


    @GetMapping("/age/list")
    @ResponseBody
    public ResponseEntity findAll(){
        LOG.log(Level.INFO, "list persons age targeted");
        List<AgeDTO> ages = ageService.findAll();
        return new ResponseEntity(ages, HttpStatus.OK);
    }

    @GetMapping("/age/{personneId}")
    @ResponseBody
    public ResponseEntity find(@PathVariable UUID personneId){
        LOG.log(Level.INFO, "find person age targeted");
        AgeDTO ageDTO = ageService.find(personneId);
        return new ResponseEntity(ageDTO, HttpStatus.OK);
    }
}
