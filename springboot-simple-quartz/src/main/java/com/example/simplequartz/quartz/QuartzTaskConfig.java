package com.example.simplequartz.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.text.ParseException;

@Configuration
public class QuartzTaskConfig {
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Autowired
    @Bean
    public SchedulerFactoryBean XTaskFactoryBean(JobFactory jobFactory) throws ParseException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setSchedulerName("XTask");
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(false);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setJobFactory(jobFactory);

        JobDetail jobDetail = JobBuilder
                .newJob(XTask.class)
                .withDescription("测试定时任务执行")
                .withIdentity("XTask-15m", "XTask")
                .storeDurably().build();

        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setName("XTask-15m");
        cronTrigger.setGroup("XTask");
        cronTrigger.setCronExpression("0/5 * * * * ?");
        cronTrigger.setJobKey(jobDetail.getKey());

        schedulerFactoryBean.setTriggers(cronTrigger);
        schedulerFactoryBean.setJobDetails(jobDetail);
        return schedulerFactoryBean;
    }
}
