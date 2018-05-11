package org.spituk.spring.scheduling.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a sample class for scheduling jobs with spring.
 *
 * @author Karan Khanna
 * @version 1.0
 * @since 5/11/2018
 */
@SpringBootApplication
@EnableScheduling
public class SpringSchedulingApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringSchedulingApplication.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTimeWithFixedRate() {
        log.info("FixedRate: The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTimeWithFixedDelay() {
        log.info("FixedDelay: The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void reportCurrentTimeWithCron() {
        log.info("Cron: The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "${cron.job.time}")
    public void reportCurrentTimeWithCronFromProperties() {
        log.info("Cron from properties: The time is now {}", dateFormat.format(new Date()));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSchedulingApplication.class);
    }

}
