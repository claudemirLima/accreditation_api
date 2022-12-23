package com.accreditation.listener;

import com.accreditation.config.GenericApplicationEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AccreditationStatusListener {
    @EventListener(condition = "#event.eventName eq 'AccreditationStatusCommand'")
    public void handle(GenericApplicationEvent event) {
        log.info("Arrived! {}",event);
    }

}
