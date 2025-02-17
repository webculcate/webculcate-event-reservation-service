package com.webculcate.event.reservation.service.core.service.eventreservation;

import com.webculcate.event.reservation.service.core.model.dto.eventreservation.ScheduledEventReservationDto;
import com.webculcate.event.reservation.service.core.model.entity.eventreservation.ScheduledEventReservation;

import java.util.List;

public interface IScheduledEventReservationDtoMapper {

    List<ScheduledEventReservationDto> mapToScheduledEventReservationDto(List<ScheduledEventReservation> reservations);

    ScheduledEventReservationDto mapToScheduledEventReservationDto(ScheduledEventReservation reservation);

}
