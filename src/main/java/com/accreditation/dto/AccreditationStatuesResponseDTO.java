package com.accreditation.dto;

import com.accreditation.type.AccreditationStatus;
import com.accreditation.type.AccreditationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccreditationStatuesResponseDTO {

    @JsonProperty("accreditation_id")
    private UUID id;

    @JsonProperty("accreditation_type")
    private AccreditationType accreditationType;


    @JsonProperty("accreditation_status")
    private AccreditationStatus accreditationStatus;


}
