package com.webculcate.event.reservation.service.core.service.eventreservation.factory.impl;

import com.webculcate.event.reservation.service.core.constant.ScheduledEventReservationStatus;
import com.webculcate.event.reservation.service.core.model.entity.embedded.TimeLog;
import com.webculcate.event.reservation.service.core.model.entity.eventreservation.ScheduledEventReservation;
import com.webculcate.event.reservation.service.core.service.eventreservation.factory.IEventReservationFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultEventReservationFactory implements IEventReservationFactory {

    @Override
    public ScheduledEventReservation generateScheduledEventReservation(Long customerId, Long scheduledEventId) {
        return ScheduledEventReservation.builder()
                .scheduledEventId(scheduledEventId)
                .status(ScheduledEventReservationStatus.PENDING)
                .customerId(customerId)
                .timeLog(new TimeLog())
                .build();
    }

}
