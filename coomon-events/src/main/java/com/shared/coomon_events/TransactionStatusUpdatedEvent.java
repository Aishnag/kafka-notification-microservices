package com.shared.coomon_events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionStatusUpdatedEvent {

    private String transactionId;
    private String customerId;

    private TransactionType type;
    private TransactionStatus status;

    private BigDecimal amount;

    private String email;
    private String mobileNumber;

    private Instant updatedAt;
}

