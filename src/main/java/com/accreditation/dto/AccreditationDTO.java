package com.accreditation.dto;

import com.accreditation.type.AccreditationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccreditationDTO implements Serializable {

    @NotNull
    @JsonProperty("user_id")
    @ApiModelProperty(example = "87bb6030-458e-11ed-b023-039b275a916a", notes = "User identify")
    private UUID userID;

    private UUID id;

    @NotNull
    @ApiModelProperty(notes = "One document is required")
    @JsonProperty("document")
    private DocumentDTO document;

    @NotNull
    @JsonProperty("accreditation_type")
    @ApiModelProperty(notes = "Type of accreditation is required")
    private AccreditationType accreditationType;
    @Builder
    public AccreditationDTO(UUID id,UUID userID,DocumentDTO document,  AccreditationType accreditationType) {
        this.id = id;
        this.userID = userID;
        this.document = document;
        this.accreditationType = accreditationType;
    }
}
