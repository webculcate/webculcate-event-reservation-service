package com.webculcate.event.reservation.service.core.service.payment.factory.impl;

import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentRequest;
import com.webculcate.event.reservation.service.core.model.entity.embedded.TimeLog;
import com.webculcate.event.reservation.service.core.model.entity.payment.Payment;
import com.webculcate.event.reservation.service.core.service.payment.factory.IPaymentFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultPaymentFactory implements IPaymentFactory {

    @Override
    public Payment generatePayment(PaymentRequest request) {
        return Payment.builder()
                .purchasedBy(request.getPurchasedBy())
                .amount(request.getAmount())
                .paymentOperation(request.getPaymentOperation())
                .timeLog(new TimeLog())
                .build();
    }

}
