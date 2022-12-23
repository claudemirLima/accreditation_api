package com.accreditation.config;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class GenericApplicationEvent extends ApplicationEvent {

    private String eventName;
    public GenericApplicationEvent(Object source, String eventName) {
        super(source);
        this.eventName = eventName;
    }
}
