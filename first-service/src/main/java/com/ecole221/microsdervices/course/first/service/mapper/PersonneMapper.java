package com.ecole221.microsdervices.course.first.service.mapper;

import com.ecole221.microsdervices.course.common.service.dto.PersonneDTO;
import com.ecole221.microsdervices.course.first.service.entity.Personne;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Component
public class PersonneMapper {


    public Personne personneDtoToPersonneEntity(PersonneDTO personneDTO) throws ParseException {
        return Personne.builder()
                .id(personneDTO.getId())
                .nomComplet(personneDTO.getNomComplet())
                .dateNaissance(new SimpleDateFormat("dd/MM/yyyy").parse(personneDTO.getDateNaissance()))
                .build();
    }

    public PersonneDTO personneEntityToPersonneDto(Personne personne){
        return PersonneDTO.builder()
                .id(personne.getId())
                .nomComplet(personne.getNomComplet())
                .dateNaissance(new SimpleDateFormat("dd/MM/yyyy").format(personne.getDateNaissance()))
                .build();
    }
}
