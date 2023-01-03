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
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccreditationCommand extends AbstractCommand {

    public AccreditationDTO accreditation;

    @Builder
    AccreditationCommand(UUID taskId, CommandStatus commandStatus,AccreditationDTO accreditation) {
        super(taskId, commandStatus);
        this.accreditation = accreditation;
    }
}
