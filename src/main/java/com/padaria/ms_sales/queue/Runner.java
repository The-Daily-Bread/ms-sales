package com.padaria.ms_sales.queue;

import java.util.concurrent.TimeUnit;

import lombok.Getter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Runner implements CommandLineRunner {

    private static RabbitTemplate rabbitTemplate = null;
    private static Receiver receiver = null;
    private static final String TOPIC = "tdb-invoice-queue";

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
//        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

    public static void sendMessage(String message) {
        rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, TOPIC, message);
        System.out.println("Mandei: " + message);
    }

}