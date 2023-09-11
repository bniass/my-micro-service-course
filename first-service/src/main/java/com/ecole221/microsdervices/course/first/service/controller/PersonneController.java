package com.ecole221.microsdervices.course.first.service.controller;

import com.ecole221.microsdervices.course.common.service.dto.PersonneDTO;
import com.ecole221.microsdervices.course.first.service.entity.Personne;
import com.ecole221.microsdervices.course.first.service.exception.ErrorAgeException;
import com.ecole221.microsdervices.course.first.service.mapper.PersonneMapper;
import com.ecole221.microsdervices.course.first.service.repository.PersonneRepository;
import com.ecole221.microsdervices.course.first.service.service.PersonneService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

@RestController
public class PersonneController {

    private static final Logger LOG = LogManager.getLogger(PersonneController.class);

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @PostMapping("/personne/add")
    public ResponseEntity<Object> save(@RequestBody PersonneDTO personneDTO) throws ParseException {
        try {
            LOG.log(Level.INFO, "save person targeted");
            Personne personne = personneService.savePersonne(personneDTO);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(personne.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        catch (Exception e){
            throw new ErrorAgeException(e.getMessage());
        }

    }

    @GetMapping("/personne/list")
    @ResponseBody
    public ResponseEntity findAll(){
        LOG.log(Level.INFO, "list persons targeted");
        List<PersonneDTO> personnes = personneService.findAll();
        return new ResponseEntity(personnes, HttpStatus.OK);
    }
}
