package com.webculcate.event.reservation.service.core.model.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentResponse {

    private PaymentDto payment;

    private boolean successful;

    public boolean isNotSuccessful() {
        return !this.isSuccessful();
    }
}
