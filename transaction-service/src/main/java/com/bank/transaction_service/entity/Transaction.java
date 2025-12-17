package com.bank.transaction_service.entity;

import com.shared.coomon_events.TransactionStatus;
import com.shared.coomon_events.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private String customerId;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private BigDecimal amount;

    private String email;
    private String mobileNumber;

    private Instant updatedAt;

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Instant.now();
    }


}