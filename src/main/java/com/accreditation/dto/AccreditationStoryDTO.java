package com.accreditation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class AccreditationStoryDTO {

    @JsonProperty("user_id")
    private String userID;
    @JsonProperty("accreditation_statuses")
    private List<AccreditationStatusDTO> accreditationStatusDTOS;

    public AccreditationStoryDTO(String userID, List<AccreditationStatusDTO> accreditationStatusDTOS) {
        this.userID = userID;
        this.accreditationStatusDTOS = accreditationStatusDTOS;
    }
}
