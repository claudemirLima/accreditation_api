package com.accreditation.batch;

import com.accreditation.domain.Accreditation;
import com.accreditation.domain.AccreditationStory;
import com.accreditation.type.AccreditationStatus;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccreditationStatusProcessor implements ItemProcessor<AccreditationStory,AccreditationStory> {


    @Override
    public AccreditationStory process(AccreditationStory accreditationStory) throws Exception {

        return  AccreditationStory.builder()
                .id(UUID.randomUUID())
                .accreditation(Accreditation.builder().id(accreditationStory.getAccreditation().getId()).build())
                .request(LocalDateTime.now())
                .status(AccreditationStatus.CONFIRMED).build();
    }
}
