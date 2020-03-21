package cn.enjoy.jobs.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"cn.enjoy.jobs.springboot"})
public class BootApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BootApp.class, args);
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedDelayString = "5000") //单机
    public void getTask1() {
        System.out.println("任务1，当前时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0/5 * *  * * ?")
    public void getTask2() {
        System.out.println("任务2，当前时间：" + dateFormat.format(new Date()));
    }
}
