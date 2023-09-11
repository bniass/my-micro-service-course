package com.ecole221.microsdervices.course.first.service.service;

import com.ecole221.microsdervices.course.common.service.dto.PersonneDTO;
import com.ecole221.microsdervices.course.common.service.event.AgeEvent;
import com.ecole221.microsdervices.course.common.service.event.AgeStatus;
import com.ecole221.microsdervices.course.common.service.event.PersonneEvent;
import com.ecole221.microsdervices.course.common.service.event.PersonneStatus;
import com.ecole221.microsdervices.course.first.service.entity.Personne;
import com.ecole221.microsdervices.course.first.service.exception.ErrorAgeException;
import com.ecole221.microsdervices.course.first.service.mapper.PersonneMapper;
import com.ecole221.microsdervices.course.first.service.repository.PersonneRepository;
import com.ecole221.microsdervices.course.first.service.utils.MyUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {
    private static final String personneTopic = "personne-event-topic";
    private final PersonneMapper personneMapper;
    private final PersonneRepository personneRepository;
    private final KafkaTemplate<String, PersonneEvent> kafkaTemplate;

    public PersonneService(PersonneMapper personneMapper, PersonneRepository personneRepository, KafkaTemplate<String, PersonneEvent> kafkaTemplate) {
        this.personneMapper = personneMapper;
        this.personneRepository = personneRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<PersonneDTO> findAll(){
        return personneRepository.findAll().
                stream().map(p-> new PersonneDTO(
                        p.getId(), p.getNomComplet(), new SimpleDateFormat("dd/MM/yyyy").format(p.getDateNaissance()),
                        p.getPersonneStatus(), p.getAgeStatus()
                )
        ).toList();
    }

    @Transactional
    public Personne savePersonne(PersonneDTO personneDTO) throws Exception, ErrorAgeException {
        try {
            checkAge(personneDTO.getDateNaissance());
        }
        catch (Exception e){
            throw new ErrorAgeException(e.getMessage());
        }
        personneDTO.setPersonne_status(PersonneStatus.CREATED);
        personneDTO.setAgeStatus(AgeStatus.INIT);
        Personne personne = personneRepository.save(personneMapper.personneDtoToPersonneEntity(personneDTO));
        personneDTO.setId(personne.getId());
        PersonneEvent personneEvent =  new PersonneEvent(personneDTO, PersonneStatus.CREATED);
        //send to kafka for update age
        kafkaTemplate.send(personneTopic, personneEvent);
        return personne;
    }

    private void checkAge(String dateNaissance) throws Exception, ErrorAgeException {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateNaissance);
        }
        catch(Exception e){
            throw new Exception("Date invalide !");
        }
        if(MyUtils.getDiffYears(date, new Date()) < 18){
            throw new ErrorAgeException("Date invalide ! [age doit etre >= 18]");
        }
    }

    @Transactional
    public void updatePersonne(AgeEvent ageEvent) {
        Optional<Personne> personne = personneRepository.findById(ageEvent.getAgeDTO().getPersonneId());
        if(!personne.isEmpty()){
            boolean isAgeSaved = AgeStatus.UPDATED.equals(ageEvent.getAgeStatus());
            PersonneStatus personneStatus = isAgeSaved?PersonneStatus.COMPLETED:PersonneStatus.ERROR_AGE;
            personne.get().setPersonneStatus(personneStatus);
            personne.get().setAgeStatus(AgeStatus.UPDATED);
            if(!isAgeSaved){
                personne.get().setAgeStatus(AgeStatus.ERROR_AGE);
            }
        }
    }
}
