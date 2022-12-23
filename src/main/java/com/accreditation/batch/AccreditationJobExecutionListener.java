package com.accreditation.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;

@Component
public class AccreditationJobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccreditationJobExecutionListener.class);

    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("beforeJob");
    }

    public void onReadError(Exception e) {
        LOGGER.info("onReadError");
    }

    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("afterJob: " + jobExecution.getStatus());
    }
}
