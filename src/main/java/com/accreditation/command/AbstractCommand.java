package com.accreditation.command;

import com.accreditation.type.CommandStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class AbstractCommand {
    @NotNull
    @JsonProperty("task_id")
    protected UUID taskId;
    @NotNull
    @JsonProperty("command_status")
    protected CommandStatus commandStatus;

}
