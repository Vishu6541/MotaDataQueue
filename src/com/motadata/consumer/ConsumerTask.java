package com.motadata.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.motadata.config.MetricsConfig;
import com.motadata.queue.MessageQueue;

public class ConsumerTask implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(ConsumerTask.class);
	private final MessageQueue messageQueue;
	private final MetricsConfig metricsConfig;

	public ConsumerTask(MessageQueue messageQueue,MetricsConfig metricsConfig) {
		this.messageQueue = messageQueue;
		this.metricsConfig= metricsConfig;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String message = messageQueue.consume();
				log.info("Consume Message = {}", message);
				if ("exit".equalsIgnoreCase(message)) {
					log.info("Consumer: Received exit signal. Shutting down.");
					break;
				}
				System.out.println("Consumer: " + message);
				metricsConfig.incrementSuccess();
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			metricsConfig.incrementErrors();
			Thread.currentThread().interrupt();
			log.error("The Consumer thread is being interrupted.");
		}
	}
}
