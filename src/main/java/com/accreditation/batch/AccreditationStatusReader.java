package com.accreditation.batch;

import com.accreditation.domain.AccreditationStory;
import com.accreditation.repository.AccreditationStoryRepository;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.*;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.batch.core.StepExecution;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

public class AccreditationStatusReader implements ItemReader<AccreditationStory> {

    @Autowired
    AccreditationStoryRepository accreditationStoryRepository;
    private Iterator<AccreditationStory> accreditationStoryIterator;


    @BeforeStep
    public void before(StepExecution stepExecution) {
        accreditationStoryIterator = accreditationStoryRepository.findAccreditationOutOfDate(LocalDate.now()).iterator();
    }

    @Override
    public AccreditationStory read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (accreditationStoryIterator != null && accreditationStoryIterator.hasNext()) {
            return accreditationStoryIterator.next();
        } else {
            return null;
        }
    }
}
