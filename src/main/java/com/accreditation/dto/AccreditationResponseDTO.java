package com.accreditation.dto;

import com.accreditation.type.AccreditationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
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
public class AccreditationResponseDTO {

    @JsonProperty("user_id")
    private UUID userID;

    @JsonProperty("accreditation_statuses")
    private List<AccreditationStatuesResponseDTO> accreditationStatuesResponseDTOS;
}
