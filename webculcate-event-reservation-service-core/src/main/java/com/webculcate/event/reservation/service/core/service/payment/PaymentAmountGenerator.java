package com.webculcate.event.reservation.service.core.service.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentAmountGenerator {
    /*
    * This is just a placeholder implementation
    * Ideally we should create a separate flow/API to assign prices to individual scheduled events
    * considering different factors like offers, discounts, etc.
    */
    public Double generateAmount(Long scheduledEventId, Set<Long> customerIds) {
        int count = customerIds.size();
        return count*100.0;
    }

}
