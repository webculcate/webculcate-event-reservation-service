package com.webculcate.event.reservation.service.core.service.payment.impl;

import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentRequest;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentResponse;
import com.webculcate.event.reservation.service.core.model.entity.embedded.TimeLog;
import com.webculcate.event.reservation.service.core.model.entity.payment.Payment;
import com.webculcate.event.reservation.service.core.repository.payment.PaymentRepository;
import com.webculcate.event.reservation.service.core.service.payment.IPaymentDtoMapper;
import com.webculcate.event.reservation.service.core.service.payment.IPaymentService;
import com.webculcate.event.reservation.service.core.service.payment.factory.IPaymentFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.webculcate.event.reservation.service.core.constant.EventReservationStrategyType.DEFAULT_EVENT_RESERVATION_SERVICE;
import static com.webculcate.event.reservation.service.core.constant.PaymentServiceStrategyType.DEFAULT_PAYMENT_SERVICE;

@Slf4j
@Service(DEFAULT_PAYMENT_SERVICE)
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentRepository repository;

    private final IPaymentFactory factory;

    private final IPaymentDtoMapper paymentDtoMapper;

    /*
     * This is just a placeholder implementation
     * Ideally a separate microservice should be created
    */
    @Override
    public PaymentResponse pay(PaymentRequest request) {
        Payment payment = factory.generatePayment(request);
        Payment savedPayment = repository.save(payment);
        return new PaymentResponse(paymentDtoMapper.mapToPaymentDto(savedPayment), true);
    }
}
