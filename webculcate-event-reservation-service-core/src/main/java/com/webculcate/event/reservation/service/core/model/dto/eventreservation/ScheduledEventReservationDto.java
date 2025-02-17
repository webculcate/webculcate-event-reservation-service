package com.webculcate.event.reservation.service.core.model.dto.eventreservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.webculcate.event.reservation.service.core.constant.ScheduledEventReservationStatus;
import com.webculcate.event.reservation.service.core.model.dto.general.TimeLogDto;
import com.webculcate.event.reservation.service.core.model.dto.payment.PaymentDto;
import com.webculcate.event.reservation.service.core.model.entity.embedded.TimeLog;
import com.webculcate.event.reservation.service.core.model.entity.payment.Payment;
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
public class ScheduledEventReservationDto {

    private Long scheduledEventReservationId;

    private ScheduledEventReservationStatus status;

    private PaymentDto payment;

    private UserDto customer;

    private TimeLogDto timeLog;

    public static ScheduledEventReservationDto initializeBlankScheduledEventReservationDto() {
        return ScheduledEventReservationDto.builder()
                .timeLog(new TimeLogDto())
                .build();
    }
}
