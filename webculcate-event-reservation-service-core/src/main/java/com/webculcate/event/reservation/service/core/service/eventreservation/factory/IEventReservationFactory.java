package com.webculcate.event.reservation.service.core.service.eventreservation.factory;

import com.webculcate.event.reservation.service.core.model.entity.eventreservation.ScheduledEventReservation;

public interface IEventReservationFactory {

    ScheduledEventReservation generateScheduledEventReservation(Long customerId, Long scheduledEventId);

}
