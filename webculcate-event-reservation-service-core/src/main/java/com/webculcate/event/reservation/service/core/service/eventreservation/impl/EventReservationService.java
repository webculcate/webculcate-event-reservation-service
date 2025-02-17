package com.webculcate.event.reservation.service.core.service.eventreservation.impl;

import com.webculcate.event.reservation.service.core.exception.eventreservation.InvalidCapacityException;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.*;
import com.webculcate.event.reservation.service.core.model.entity.eventreservation.ScheduledEventReservation;
import com.webculcate.event.reservation.service.core.service.eventreservation.transaction.EventReservationTransactionService;
import com.webculcate.event.reservation.service.core.service.eventreservation.IEventReservationService;
import com.webculcate.event.reservation.service.core.service.eventreservation.IScheduledEventReservationDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.webculcate.event.reservation.service.core.constant.APIMetadata.SCHEDULED_EVENT_RESERVATION;
import static com.webculcate.event.reservation.service.core.constant.EventReservationStrategyType.DEFAULT_EVENT_RESERVATION_SERVICE;
import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.ZERO_INTEGER;
import static com.webculcate.event.reservation.service.core.utility.ServiceHelper.nullHandledExtraction;

@Slf4j
@Service(DEFAULT_EVENT_RESERVATION_SERVICE)
@RequiredArgsConstructor
public class EventReservationService implements IEventReservationService {

    private final EventReservationTransactionService eventReservationTransactionService;

    private final IScheduledEventReservationDtoMapper scheduledEventReservationDtoMapper;

    @Override
    public EventReservationCreationResponse createEventReservation(EventReservationCreationRequest request) {
        Optional<Integer> optionalCapacity = nullHandledExtraction(() -> request.getCustomerIds().size());
        Integer capacity = optionalCapacity.orElse(ZERO_INTEGER);
        if (capacity <= ZERO_INTEGER)
            throw new InvalidCapacityException();
        List<ScheduledEventReservation> reservations = eventReservationTransactionService.createEventReservationTransaction(request, capacity);
        return new EventReservationCreationResponse(
                SCHEDULED_EVENT_RESERVATION.getSuccessMessage(),
                scheduledEventReservationDtoMapper.mapToScheduledEventReservationDto(reservations)
        );
    }

    @Override
    public EventReservationPaginationResponse getEventReservationList(EventReservationPaginationRequest request) {
        List<ScheduledEventReservation> resultList = eventReservationTransactionService.getEventReservationList(request);
        List<ScheduledEventReservationDto> reservations = scheduledEventReservationDtoMapper.mapToScheduledEventReservationDto(resultList);
        return new EventReservationPaginationResponse(reservations, request.getPageIndex(), request.getPageSize());
    }


}
