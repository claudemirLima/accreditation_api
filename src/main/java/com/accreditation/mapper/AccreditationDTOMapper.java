package com.accreditation.mapper;

import com.accreditation.domain.Accreditation;
import com.accreditation.domain.Document;
import com.accreditation.dto.AccreditationDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AccreditationDTOMapper {

    public Accreditation map(AccreditationDTO accreditationDTO) {
        var accreditation = Accreditation.builder()
                .id(UUID.randomUUID())
                .type(accreditationDTO.getAccreditationType())
                .userID(accreditationDTO.getUserID())
                .build();

        accreditation.setDocuments(List.of(Document.builder()
                .content(accreditationDTO.getDocument().getContent())
                .mimeType(accreditationDTO.getDocument().getMimeType())
                .name(accreditationDTO.getDocument().getName())
                .accreditation(accreditation).build()));
        return accreditation;
    }
}
