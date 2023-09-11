package com.ecole221.microsdervices.course.common.service.event;

import com.ecole221.microsdervices.course.common.service.dto.PersonneDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class PersonneEvent implements Event {

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private PersonneDTO personneDTO;
    private PersonneStatus personneStatus;

    public PersonneEvent(PersonneDTO personneDTO, PersonneStatus personneStatus) {
        this.personneDTO = personneDTO;
        this.personneStatus = personneStatus;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getEventDate() {
        return eventDate;
    }
}
