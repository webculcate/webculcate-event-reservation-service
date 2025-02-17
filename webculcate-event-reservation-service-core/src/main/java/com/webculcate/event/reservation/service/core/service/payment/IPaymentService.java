package com.webculcate.event.reservation.service.core.service.payment;

import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentRequest;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentResponse;

public interface IPaymentService {

    PaymentResponse pay(PaymentRequest request);

}
