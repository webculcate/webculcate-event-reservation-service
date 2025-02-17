package com.webculcate.event.reservation.service.core.service.payment;

import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentDto;
import com.webculcate.event.reservation.service.core.model.entity.payment.Payment;

public interface IPaymentDtoMapper {

    PaymentDto mapToPaymentDto(Payment savedPayment);

}
