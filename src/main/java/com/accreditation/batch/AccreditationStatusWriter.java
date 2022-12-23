package com.accreditation.batch;

import com.accreditation.domain.AccreditationStory;
import com.accreditation.repository.AccreditationStoryRepository;
import com.accreditation.type.AccreditationStatus;
import com.accreditation.type.CommandStatus;
import com.accreditation.util.SendCommand;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class AccreditationStatusWriter implements ItemWriter<AccreditationStory> {

    @Autowired
    private SendCommand sendCommand;

    @Autowired
    AccreditationStoryRepository accreditationStoryRepository;

    @Override
    public void write(List<? extends AccreditationStory> list) throws Exception {
        if(list != null && !list.isEmpty()) {
            list.stream().forEach(item -> {
                accreditationStoryRepository.save(item);
                sendCommand.sendCommandAccreditationStatusChange(item.getAccreditation().getId(), CommandStatus.CONFIRMED, AccreditationStatus.CONFIRMED);
            });
        }
    }
}
