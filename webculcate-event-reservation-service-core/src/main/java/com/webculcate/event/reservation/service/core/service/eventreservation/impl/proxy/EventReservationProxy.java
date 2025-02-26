package com.webculcate.event.reservation.service.core.service.eventreservation.impl.proxy;

import com.webculcate.event.reservation.service.core.exception.eventreservation.InvalidEventReservationCreationRequestException;
import com.webculcate.event.reservation.service.core.exception.eventreservation.InvalidEventReservationPaginationRequestException;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationCreationRequest;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationCreationResponse;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationPaginationRequest;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationPaginationResponse;
import com.webculcate.event.reservation.service.core.service.eventreservation.IEventReservationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.webculcate.event.reservation.service.core.constant.EventReservationStrategyType.EVENT_RESERVATION_SERVICE_PROXY;
import static com.webculcate.event.reservation.service.core.constant.ServiceConstant.STRING_SPACE;

@Slf4j
@Service(EVENT_RESERVATION_SERVICE_PROXY)
@RequiredArgsConstructor
public class EventReservationProxy implements IEventReservationService {

    private final Validator serviceValidator;

    private final IEventReservationService eventReservationService;

    @Override
    public EventReservationCreationResponse createEventReservation(EventReservationCreationRequest request) {
        Set<ConstraintViolation<EventReservationCreationRequest>> validationResults = serviceValidator.validate(request);
        if (!validationResults.isEmpty()) {
            List<String> errorMessageList = generateErrorMessageList(validationResults);
            throw new InvalidEventReservationCreationRequestException(errorMessageList);
        }
        log.info("Validation successful for createEventReservation");
        return eventReservationService.createEventReservation(request);
    }

    @Override
    public EventReservationPaginationResponse getEventReservationList(EventReservationPaginationRequest request) {
        Set<ConstraintViolation<EventReservationPaginationRequest>> validationResults = serviceValidator.validate(request);
        if (!validationResults.isEmpty()) {
            List<String> errorMessageList = generateErrorMessageList(validationResults);
            throw new InvalidEventReservationPaginationRequestException(errorMessageList);
        }
        log.info("Validation successful for getEventReservationList");
        return eventReservationService.getEventReservationList(request);
    }

    private <T> List<String> generateErrorMessageList(Set<ConstraintViolation<T>> validationResults) {
        return validationResults.stream()
                .map(result -> result.getPropertyPath() + STRING_SPACE + result.getMessage())
                .toList();
    }

}
