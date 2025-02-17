package com.webculcate.event.reservation.service.core.repository.payment;

import com.webculcate.event.reservation.service.core.model.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
