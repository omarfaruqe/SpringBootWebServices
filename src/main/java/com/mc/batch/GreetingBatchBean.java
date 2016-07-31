package com.mc.batch;


import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mc.service.GreetingService;
import com.mc.model.*;

@Component
public class GreetingBatchBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GreetingService greetingService;

	//1st type of Scheduling
    //@Scheduled(cron = "0,30 * * * * *")
    public void cronJob() {
        logger.info("> cronJob");

        // Add scheduled logic here
        Collection<Greeting> greetings = greetingService.findAll();
        logger.info("There are {} greetings in the data store.",
                greetings.size());

        logger.info("< cronJob");
    }

    //2nd type of Scheduling, fixedRate job
    //@Scheduled(initialDelay = 5000, fixedRate = 15000)
    public void fixedRateJobWithInitialDelay() {
        logger.info("> fixedRateJobWithInitialDelay");

        // Add scheduled logic here
        // Simulate job processing time
        long pause = 5000;
        long start = System.currentTimeMillis();
        do {
            if (start + pause < System.currentTimeMillis()) {
                break;
            }
        } while (true);
        logger.info("Processing time was {} seconds.", pause / 1000);

        logger.info("< fixedRateJobWithInitialDelay");
    }

    //3rd type of Scheduling , fixedDelay job
    @Scheduled(initialDelay = 5000, fixedDelay = 15000)
    public void fixedDelayJobWithInitialDelay() {
        logger.info("> fixedDelayJobWithInitialDelay");

        // Add scheduled logic here
        // Simulate job processing time
        long pause = 5000;
        long start = System.currentTimeMillis();
        do {
            if (start + pause < System.currentTimeMillis()) {
                break;
            }
        } while (true);
        logger.info("Processing time was {} seconds.", pause / 1000);

        logger.info("< fixedDelayJobWithInitialDelay");
    }
	
}
