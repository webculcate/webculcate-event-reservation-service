package com.webculcate.event.reservation.service.core.model.dto.eventreservation.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentResponse;
import com.webculcate.event.reservation.service.core.model.external.event.CapacityUpdateResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventReservationRollbackDto {

    private CapacityUpdateResponse capacityUpdateResponse;

    private PaymentResponse paymentResponse;

}
