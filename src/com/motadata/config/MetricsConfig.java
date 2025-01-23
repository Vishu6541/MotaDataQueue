package com.motadata.config;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.motadata.application.MainClass;

public class MetricsConfig {
	private static final Logger log = LoggerFactory.getLogger(MainClass.class);
	private final AtomicInteger successCount = new AtomicInteger(0);
    private final AtomicInteger errorCount = new AtomicInteger(0);

    public void incrementSuccess() {
        successCount.incrementAndGet();
    }

    public void incrementErrors() {
        errorCount.incrementAndGet();
    }

    public void logMetrics() {
    	log.info("============================= MESSAGE-METRICS ====================================");
    	log.info("Total Successful Messages: {}", successCount.get());
    	log.info("Total Errors: {}", errorCount.get());
    }
}
