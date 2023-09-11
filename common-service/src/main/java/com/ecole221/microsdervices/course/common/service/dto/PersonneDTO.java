package com.ecole221.microsdervices.course.common.service.dto;

import com.ecole221.microsdervices.course.common.service.event.AgeStatus;
import com.ecole221.microsdervices.course.common.service.event.PersonneStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonneDTO {
    private UUID id;
    private String nomComplet;
    private String dateNaissance;
    private PersonneStatus personne_status;
    private AgeStatus ageStatus;
}
