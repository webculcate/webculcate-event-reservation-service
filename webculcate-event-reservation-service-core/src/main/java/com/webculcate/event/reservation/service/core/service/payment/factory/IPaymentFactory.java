package com.webculcate.event.reservation.service.core.service.payment.factory;

import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentRequest;
import com.webculcate.event.reservation.service.core.model.entity.payment.Payment;

public interface IPaymentFactory {

    Payment generatePayment(PaymentRequest request);

}
