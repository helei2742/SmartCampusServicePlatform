package org.pg7.scsp.config;

import org.pg7.scsp.jobs.UpdateNewsSeeCountJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail1(){
        return JobBuilder.newJob(UpdateNewsSeeCountJob.class).storeDurably().build();
    }

    /*
     * 每3天执行一次
     * */
    @Bean
    public Trigger trigger1(){
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger1","heleidage")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * 0/20 * * ?"))
                .forJob(jobDetail1())
                .build();
    }
}

