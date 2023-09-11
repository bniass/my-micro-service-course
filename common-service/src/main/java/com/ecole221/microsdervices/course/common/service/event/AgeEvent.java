package com.ecole221.microsdervices.course.common.service.event;

import com.ecole221.microsdervices.course.common.service.dto.AgeDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class AgeEvent implements Event {

    private UUID eventId = UUID.randomUUID();
    private Date eventDate = new Date();
    private AgeDTO ageDTO;
    private AgeStatus ageStatus;

    public AgeEvent(AgeDTO ageDTO, AgeStatus ageStatus) {
        this.ageDTO = ageDTO;
        this.ageStatus = ageStatus;
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
