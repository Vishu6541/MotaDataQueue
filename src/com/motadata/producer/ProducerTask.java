package com.motadata.producer;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.motadata.queue.MessageQueue;

public class ProducerTask implements Runnable {
	private static final Logger log = LoggerFactory.getLogger(ProducerTask.class);
    private final MessageQueue messageQueue;
    public ProducerTask(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter messages to produce (type 'exit' to stop):");
            while (true) {
                System.out.print("Producer: ");
                String message = scanner.nextLine();
                log.info("Producer Message = {}",message);
                if ("exit".equalsIgnoreCase(message)) {
                    log.info("Producer Type Exit Command both thread is stoped");
                    messageQueue.produce("exit");
                    break;
                }
                
                messageQueue.produce(message);
            }
           
        
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("The producer thread is being interrupted.");
            
        }
    }
}
