package com.webculcate.event.reservation.service.core.service.payment;

import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationCreationRequest;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentRequest;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.webculcate.event.reservation.service.core.constant.PaymentServiceStrategyType.DEFAULT_PAYMENT_SERVICE;
import static com.webculcate.event.reservation.service.core.constant.PaymentServiceStrategyType.PAYMENT_SERVICE_PROXY;
import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.PROXY_ENABLED;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentManager {

    private final Map<String, IPaymentService> serviceMap;

    private final PaymentAmountGenerator amountGenerator;

    @Value(PROXY_ENABLED)
    private boolean proxyEnabled;

    public IPaymentService getPaymentService() {
        if (proxyEnabled)
            return serviceMap.get(PAYMENT_SERVICE_PROXY);
        else
            return serviceMap.get(DEFAULT_PAYMENT_SERVICE);
    }

    public PaymentResponse adaptAndPay(PaymentRequest request) {
        return getPaymentService().pay(request);
    }

    public PaymentResponse adaptAndPay(EventReservationCreationRequest request) {
        Double amount = amountGenerator.generateAmount(request.getScheduledEventId(), request.getCustomerIds());
        return getPaymentService().pay(new PaymentRequest(request.getPurchasedBy(), amount));
    }
}
