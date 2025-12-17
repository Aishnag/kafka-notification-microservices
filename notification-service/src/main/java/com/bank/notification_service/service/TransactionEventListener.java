package com.bank.notification_service.service;

import com.shared.coomon_events.TransactionStatusUpdatedEvent;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class TransactionEventListener {


    @KafkaListener(topics = "${app.kafka.topic.transaction-status-updated}",
            groupId = "notification-group")
    public void consume(TransactionStatusUpdatedEvent event) {
        System.out.println("Received Event: " + event);
    }
}
