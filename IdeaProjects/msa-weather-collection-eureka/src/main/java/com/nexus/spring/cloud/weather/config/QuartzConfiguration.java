package com.nexus.spring.cloud.weather.config;

import com.nexus.spring.cloud.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class  QuartzConfiguration  {
    private static final int TIME = 1800;

//    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

     //jobDetail 定义一个特定的job
    @Bean
    public JobDetail weatherDataSyncJobJobDetail(){
        return
                JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("WeatherDataSyncJob").storeDurably().build();
    }
     //Trigger 触发器

    @Bean
    public Trigger weatherDataSyncTrigger(){

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(TIME).
                repeatForever();
        return TriggerBuilder.newTrigger().forJob( weatherDataSyncJobJobDetail()).withIdentity("WeatherDataSyncTrigger")
                .withSchedule(scheduleBuilder).build();

    }
}
