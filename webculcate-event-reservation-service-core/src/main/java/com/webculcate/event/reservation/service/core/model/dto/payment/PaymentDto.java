package com.webculcate.event.reservation.service.core.model.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webculcate.event.reservation.service.core.constant.PaymentOperationType;
import com.webculcate.event.reservation.service.core.model.dto.general.TimeLogDto;
import com.webculcate.event.reservation.service.core.model.external.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDto {

    private Long paymentId;

    private Double amount;

    private UserDto purchasedBy;

    private PaymentOperationType paymentOperation;

    private TimeLogDto timeLog;

    public static PaymentDto initializeBlankPaymentDto() {
        return PaymentDto.builder()
                .timeLog(new TimeLogDto())
                .build();
    }

}
