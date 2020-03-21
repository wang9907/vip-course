package cn.enjoy.jobs.quartz;

import org.quartz.JobDataMap;
import org.quartz.Trigger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.enjoy.jobs.quartz"})
public class QuartzApp {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApp.class,args);
    }

    @Bean("jobDetail")
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(MyJob.class);
        JobDataMap jobDataMap =new JobDataMap();
        jobDataMap.put("name","Quartz");
        jobDetailFactoryBean.setJobDataAsMap(jobDataMap);
        return  jobDetailFactoryBean;
    }

    @Bean("cronJobTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();
        triggerFactoryBean.setJobDetail(jobDetail.getObject());
        triggerFactoryBean.setCronExpression("5/2 * * * * ?");
        return  triggerFactoryBean;
    }

    /**
     * 调度工厂，默认是StdSchedulerFactory
     * 通过getScheduler()得到调度器----StdScheduler
     * Scheduler中有一个QuartzSchedulerThread线程实例---即调度线程
     * 调度线程---不停地获取触发器---当触发器触发时，从线程池中取线程执行对应的job
     */
    @Bean("scheduler")
    public SchedulerFactoryBean schedulerFactoryBean(Trigger cronJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setOverwriteExistingJobs(true);
        bean.setStartupDelay(1);
        bean.setTriggers(cronJobTrigger);
        return  bean;
    }
}
