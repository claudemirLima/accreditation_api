package com.accreditation.util;

import com.accreditation.command.AccreditationCommand;
import com.accreditation.command.AccreditationStateCommand;
import com.accreditation.dto.AccreditationDTO;
import com.accreditation.type.AccreditationStatus;
import com.accreditation.type.CommandStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SendCommand {

    @Autowired
    private KafkaTemplate<String, Object> template;

    @Value("${kafka.topic.accreditation.command}")
    private String topicAccreditation;

    @Value("${kafka.topic.accreditation.status.command}")
    private String topicAccreditationStatus;


    public void sendCommandAccreditation(AccreditationDTO accreditationDTO, CommandStatus commandStatus) {
        var command = AccreditationCommand.builder()
                .taskId(UUID.randomUUID())
                .commandStatus(commandStatus)
                .accreditation(accreditationDTO).build();

//        String json = null;
//        try {
//            json = (new ObjectMapper()).writeValueAsString(accreditationDTO);
//
//            MessageBuilder<String> messageBuilder = MessageBuilder.withPayload(json)
//                    .setHeader("kafka_topic", topicAccreditation)
//                    .setHeader("eventName", "AccreditationStatusCommand")
//                    .setHeader("x-timestamp", Instant.now()
//                            .toString()).setHeader("guid", command.getTaskId().toString());
//            template.send(topicAccreditation,messageBuilder.build());
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }


        Message<AccreditationCommand> message = MessageBuilder
                .withPayload(command)
                .setHeader(KafkaHeaders.TOPIC, topicAccreditation)
                .setHeader("eventName", "AccreditationStatusCommand")
                .build();
        template.send(topicAccreditation,command);

    }

    public void sendCommandAccreditationStatusChange(UUID accreditationID, CommandStatus commandStatus, AccreditationStatus accreditationStatus) {
        var command = AccreditationStateCommand.builder()
                .taskId(UUID.randomUUID())
                .commandStatus(commandStatus)
                .accreditationStatus(accreditationStatus).build();

        Message<AccreditationStateCommand> message = MessageBuilder
                .withPayload(command)
                .setHeader(KafkaHeaders.TOPIC, topicAccreditationStatus)
                .setHeader("eventName", "CreateAccreditationCommand")
                .build();

        template.send(topicAccreditationStatus,command);
    }
}
