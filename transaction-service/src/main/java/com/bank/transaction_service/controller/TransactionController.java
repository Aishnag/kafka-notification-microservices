package com.bank.transaction_service.controller;

import com.bank.transaction_service.entity.Transaction;
import com.bank.transaction_service.service.TransactionService;
import com.shared.coomon_events.TransactionStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;


    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Transaction> doTransaction(@PathVariable("id") Long transactionId,
                                                     @RequestParam TransactionStatus status) {
        return ResponseEntity.ok(service.updateTransaction(transactionId, status));
    }
}
