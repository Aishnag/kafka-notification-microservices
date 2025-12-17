package com.bank.transaction_service.service;

import com.bank.transaction_service.entity.Transaction;
import com.bank.transaction_service.repository.TransactionRepository;

import com.shared.coomon_events.TransactionStatus;
import com.shared.coomon_events.TransactionStatusUpdatedEvent;
import com.shared.coomon_events.TransactionType;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@EnableKafka
public class TransactionService {

    private final TransactionRepository repo;
    private final KafkaTemplate<String, TransactionStatusUpdatedEvent> kafkaTemplate;

    @Value("${app.kafka.topic.transaction-status-updated}")
    private String topic;

    @Transactional
    public Transaction updateTransaction(Long transactionId, TransactionStatus status) {

        Transaction tx = repo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Id Not Found: " + transactionId));

        tx.setStatus(status);
        tx.setUpdatedAt(Instant.now());
        repo.save(tx);

        // Build and publish event
        TransactionStatusUpdatedEvent event = new TransactionStatusUpdatedEvent(
                tx.getTransactionId().toString(),
                tx.getCustomerId(),
                tx.getType(),
                tx.getStatus(),
                tx.getAmount(),
                tx.getEmail(),
                tx.getMobileNumber(),
                tx.getUpdatedAt()
        );

        kafkaTemplate.send(topic, tx.getTransactionId().toString(), event);

        return tx;
    }
}
