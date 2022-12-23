package com.accreditation.command.handle;

import com.accreditation.command.AccreditationCommand;
import com.accreditation.config.GenericApplicationEvent;
import com.accreditation.dto.AccreditationDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AccreditationHandle {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @KafkaListener(
            topics = "${kafka.topic.accreditation.status.command}"
            , groupId = "${spring.kafka.consumer_group_id}"
            , containerFactory = "kafkaListenerContainerFactory")
    public void handle(@Payload AccreditationCommand accreditationCommand, @Headers MessageHeaders messageHeaders){

        log.info("Header - {} ",messageHeaders.get("eventName"));
        log.info("Command {} received - {} ",messageHeaders.get("eventName"),accreditationCommand);

        GenericApplicationEvent genericApplicationEvent =
                new GenericApplicationEvent(accreditationCommand,messageHeaders.get("eventName").toString());

        applicationEventPublisher.publishEvent(genericApplicationEvent);
    }
}
