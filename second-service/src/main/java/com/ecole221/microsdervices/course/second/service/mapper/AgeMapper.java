package com.ecole221.microsdervices.course.second.service.mapper;

import com.ecole221.microsdervices.course.common.service.dto.AgeDTO;
import com.ecole221.microsdervices.course.second.service.entity.AgeEntity;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;

@Component
public class AgeMapper {

    public AgeDTO AgeEntityToAgeDto(AgeEntity ageEntity){
        return AgeDTO.builder()
                .personneId(ageEntity.getPersonneId())
                .age(ageEntity.getAge())
                .build();
    }
}
