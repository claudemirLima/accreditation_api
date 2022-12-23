package com.accreditation.dto;

import com.accreditation.type.AccreditationStatus;
import com.accreditation.type.AccreditationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AccreditationStatusDTO {


    @JsonProperty("accreditation_type")
    private AccreditationType accreditationType;


    @JsonProperty("accreditation_status")
    private AccreditationStatus accreditationStatus;

    public AccreditationStatusDTO(AccreditationType accreditationType, AccreditationStatus accreditationStatus) {
        this.accreditationType = accreditationType;
        this.accreditationStatus = accreditationStatus;
    }
}
