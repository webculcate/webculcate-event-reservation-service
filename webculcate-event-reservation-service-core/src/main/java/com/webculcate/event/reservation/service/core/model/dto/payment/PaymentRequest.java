package com.webculcate.event.reservation.service.core.model.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webculcate.event.reservation.service.core.constant.PaymentOperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentRequest {

    private Long purchasedBy;

    private Double amount;

    private PaymentOperationType paymentOperation;

}
