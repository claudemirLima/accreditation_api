package com.accreditation.config;

import com.accreditation.batch.*;
import com.accreditation.domain.AccreditationStory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobBatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public AccreditationStatusReader reader() {
        return new AccreditationStatusReader();
    }

    @Bean
    public AccreditationStatusProcessor processor() {
        return new AccreditationStatusProcessor();
    }

    @Bean
    public AccreditationStatusWriter writer() {
        return new AccreditationStatusWriter();
    }

    @Bean
    public AccreditationJobExecutionListener jobExecutionListener() {
        return new AccreditationJobExecutionListener();
    }

    @Bean
    public AccreditationStatusItemReaderListener accreditationStatusItemReaderListener() {
        return new AccreditationStatusItemReaderListener();
    }

    @Bean
    public Job job(Step step, AccreditationJobExecutionListener jobExecutionListener) {
        Job job = jobBuilderFactory.get("job1")
                .listener(jobExecutionListener)
                .flow(step)
                .end()
                .build();
        return job;
    }

    @Bean
    public Step step(AccreditationStatusReader reader,
                     AccreditationStatusWriter writer,
                     AccreditationStatusProcessor processor,
                     AccreditationStatusItemReaderListener accreditationStatusItemReaderListener  ) {

        TaskletStep step = stepBuilderFactory.get("step1")
                .<AccreditationStory, AccreditationStory>chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(accreditationStatusItemReaderListener)
                .build();
        return step;
    }
}
