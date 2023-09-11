package com.ecole221.microsdervices.course.first.service.entity;

import com.ecole221.microsdervices.course.common.service.event.AgeStatus;
import com.ecole221.microsdervices.course.common.service.event.PersonneStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(length = 120)
    private String nomComplet;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    @Column(name = "personne_status")
    private PersonneStatus personneStatus;
    @Column(name = "age_status")
    private AgeStatus ageStatus;
}
