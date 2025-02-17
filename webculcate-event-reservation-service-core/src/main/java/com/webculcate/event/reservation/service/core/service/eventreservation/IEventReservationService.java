package com.webculcate.event.reservation.service.core.service.eventreservation;

import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationCreationRequest;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationCreationResponse;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationPaginationRequest;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationPaginationResponse;

public interface IEventReservationService {

    EventReservationCreationResponse createEventReservation(EventReservationCreationRequest request);

    EventReservationPaginationResponse getEventReservationList(EventReservationPaginationRequest request);

}
