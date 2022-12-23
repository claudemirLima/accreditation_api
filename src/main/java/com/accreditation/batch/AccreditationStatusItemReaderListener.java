package com.accreditation.batch;

import com.accreditation.domain.AccreditationStory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;


public class AccreditationStatusItemReaderListener implements ItemReadListener<AccreditationStory> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccreditationStatusItemReaderListener.class);

    @Override
    public void beforeRead() {

    }


    @Override
    public void afterRead(AccreditationStory accreditationStory) {

    }

    @Override
    public void onReadError(Exception e) {
        LOGGER.info("onReadError");
    }
}
