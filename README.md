# webculcate event reservation service

## Description
This service manages the life cycle of reservations for event schedules. Moreover, it is like an orchestrator for handling communication with other services which is essential for creating reservations. (Distributed Transactions)
As of now, I am using a placeholder service for payments. Ideally, payments should be handled by a separate system or cluster of services which ensures independent scalability.

## Architecture
<img width="1411" alt="webculcateArchitecture" src="https://github.com/user-attachments/assets/4a11b598-3713-4ac7-89d8-7743199740af" />

## Functionalities
1. create reservations for an event schedule which consists of following steps (Distributed Transaction)
   * reduce and reserve capacity till transaction is complete
   * payment
   * confirm reservation
   * rollback if any of the above fails (SAGA design pattern)
2. fetch list of reservations for an event schedule
