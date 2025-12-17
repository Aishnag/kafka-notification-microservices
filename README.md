# Banking Microservices Demo

This project demonstrates a microservices-based architecture using Spring Boot.

## Microservices
- common-event: Shared Kafka event models and enums
- Transaction Service (Producer)
- Notification Service (Consumer)

## Tech Stack
- Java 8
- Spring Boot
- Apache Kafka
- REST APIs
- MySQL (optional)
- Maven

## Features
- Asynchronous communication using Kafka
- Transaction status updates
- Event-driven architecture
## Start Kafka (KRaft mode)

# 1. Initialize storage with a random UUID
.\bin\windows\kafka-storage.bat random-uuid

# 2. Format the storage for standalone mode
.\bin\windows\kafka-storage.bat format --standalone -t 3itdRdU5TVaLL_F8-dCNUw -c config\server.properties

# 3. Start Kafka server
.\bin\windows\kafka-server-start.bat .\config\server.properties

## How to Run
1. Start Apache Kafka in KRaft mode (without Zookeeper)
2. Create required Kafka topics
2. Run Transaction Service
3. Run Notification Service

## Sample PUT Request to Update Transaction Status

PUT http://localhost:8080/transactions/1/status?status=FAILED

Content-Type: application/json

{
    "transactionId": 1,
    "customerId": "CUST123",
    "type": "DEBIT",
    "status": "FAILED",
    "amount": 5000.00,
    "email": "customer@example.com",
    "mobileNumber": "9876543210",
    "updatedAt": "2025-12-17T06:00:45.000001700Z"
}


## Author
Aishwarya Nagare
