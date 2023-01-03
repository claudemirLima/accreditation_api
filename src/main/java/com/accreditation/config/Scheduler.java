package com.accreditation.config;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.batch.core.Job;

@Component
public class Scheduler {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws Exception{
        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution =  jobLauncher.run(job, param);
        System.out.println("Job Execution Status: " + execution.getStatus());
    }

}
