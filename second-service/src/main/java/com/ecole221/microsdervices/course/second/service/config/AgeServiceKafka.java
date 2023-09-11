package com.ecole221.microsdervices.course.second.service.config;


import com.ecole221.microsdervices.course.common.service.event.AgeEvent;
import com.ecole221.microsdervices.course.common.service.event.PersonneEvent;
import com.ecole221.microsdervices.course.second.service.service.AgeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Component
public class AgeServiceKafka {
    private static final String ageTopic = "age-event-topic";

    private final AgeService ageService;
    private final KafkaTemplate<String, AgeEvent> kafkaTemplate;

    public AgeServiceKafka(AgeService ageService, KafkaTemplate<String, AgeEvent> kafkaTemplate) {
        this.ageService = ageService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "personne-event-topic", groupId = "default", containerFactory
            = "PersonneEventListner")
    public void consumeMessage(PersonneEvent personneEvent) throws JsonProcessingException, ParseException {
        log.info("message consumed {}", personneEvent.getEventId());
        AgeEvent ageEvent = ageService.saveAge(personneEvent);
        kafkaTemplate.send(ageTopic, ageEvent);
    }




}
