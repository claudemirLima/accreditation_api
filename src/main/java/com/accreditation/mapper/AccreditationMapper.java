package com.accreditation.mapper;

import com.accreditation.domain.Accreditation;
import com.accreditation.domain.AccreditationStory;
import com.accreditation.domain.Document;
import com.accreditation.dto.AccreditationDTO;
import com.accreditation.dto.AccreditationResponseDTO;
import com.accreditation.dto.AccreditationStatuesResponseDTO;
import com.accreditation.repository.AccreditationStoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccreditationMapper {

    public AccreditationResponseDTO map(List<Accreditation> accreditations) {
        var response = AccreditationResponseDTO.builder()
                .accreditationStatuesResponseDTOS(new ArrayList<>())
                .build();
        response.setUserID(accreditations.get(0).getUserID());

        accreditations.stream().forEach(item->{
            response.getAccreditationStatuesResponseDTOS().add(mapToList(item.getAccreditationStories()));

        });
        return response;
    }

    private AccreditationStatuesResponseDTO mapToList(List<AccreditationStory> accreditationStories) {
        var last = accreditationStories.get(accreditationStories.size()-1);
        return AccreditationStatuesResponseDTO.builder()
                    .accreditationStatus(last.getStatus())
                    .id(last.getAccreditation().getId())
                    .accreditationType(last.getAccreditation().getType())
                    .build();
    }
}
