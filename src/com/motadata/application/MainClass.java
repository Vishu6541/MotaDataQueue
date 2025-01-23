package com.motadata.application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.motadata.config.MetricsConfig;
import com.motadata.consumer.ConsumerTask;
import com.motadata.producer.ProducerTask;
import com.motadata.queue.MessageQueue;

public class MainClass {
	private static final Logger log = LoggerFactory.getLogger(MainClass.class);

	public static void main(String[] args) {
		log.info("Motadata Message Queue Application is Start");
		MessageQueue messageQueue = new MessageQueue();
		MetricsConfig metricsConfig = new MetricsConfig();
		
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
		
        executorService.submit(new ProducerTask(messageQueue));
		executorService.submit(new ConsumerTask(messageQueue, metricsConfig));
	
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            metricsConfig.logMetrics();
        }, 0, 1, TimeUnit.MINUTES);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			executorService.shutdownNow();
			log.info("Application shutting down.");
		}));
	}
}
