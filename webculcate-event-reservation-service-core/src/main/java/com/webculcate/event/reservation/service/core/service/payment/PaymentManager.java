package com.webculcate.event.reservation.service.core.service.payment;

import com.webculcate.event.reservation.service.core.constant.PaymentOperationType;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationCreationRequest;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentRequest;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.webculcate.event.reservation.service.core.constant.PaymentServiceStrategyType.DEFAULT_PAYMENT_SERVICE;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentManager {

    private final Map<String, IPaymentService> serviceMap;

    private final PaymentAmountGenerator amountGenerator;

    public IPaymentService getPaymentService() {
            return serviceMap.get(DEFAULT_PAYMENT_SERVICE);
    }

    public PaymentResponse pay(PaymentRequest request) {
        return getPaymentService().pay(request);
    }

    public PaymentResponse adaptAndPay(EventReservationCreationRequest request, PaymentOperationType paymentOperation) {
        Double amount = amountGenerator.generateAmount(request.getScheduledEventId(), request.getCustomerIds());
        return getPaymentService().pay(new PaymentRequest(request.getPurchasedBy(), amount, paymentOperation));
    }
}
