package com.padaria.ms_sales.queue;

import java.util.concurrent.CountDownLatch;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

//    public void receiveMessage(byte[] message) {
//        String messageString = new String(message);
//        System.out.println("Received <" + messageString + ">");
//        latch.countDown();
//    }

//    public void receiveString(String message) {
//        System.out.println("Received <" + message + ">");
//        latch.countDown();
//    }
}