package com.accreditation.command;

import com.accreditation.dto.AccreditationDTO;
import com.accreditation.type.AccreditationStatus;
import com.accreditation.type.AccreditationType;
import com.accreditation.type.CommandStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccreditationStateCommand extends AbstractCommand implements Serializable {

    private UUID id;

    @JsonProperty("accreditation_status")
    private AccreditationStatus accreditationStatus;
    @Builder
    public AccreditationStateCommand(UUID taskId, CommandStatus commandStatus, UUID id, AccreditationStatus accreditationStatus) {
        super(taskId, commandStatus);
        this.id = id;
        this.accreditationStatus = accreditationStatus;
    }
}
