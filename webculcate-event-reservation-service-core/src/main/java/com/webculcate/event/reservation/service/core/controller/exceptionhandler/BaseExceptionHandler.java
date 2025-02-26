package com.webculcate.event.reservation.service.core.controller.exceptionhandler;

import com.webculcate.event.reservation.service.core.exception.eventreservation.InvalidCapacityException;
import com.webculcate.event.reservation.service.core.exception.eventreservation.InvalidEventReservationCreationRequestException;
import com.webculcate.event.reservation.service.core.exception.eventreservation.InvalidEventReservationPaginationRequestException;
import com.webculcate.event.reservation.service.core.model.dto.general.ServiceExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.webculcate.event.reservation.service.core.constant.ServiceExceptionType.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(InvalidEventReservationCreationRequestException.class)
    public ResponseEntity<ServiceExceptionResponse> handleInvalidEventReservationCreationRequestException(InvalidEventReservationCreationRequestException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        INVALID_EVENT_RESERVATION_CREATION_REQUEST,
                        INVALID_EVENT_RESERVATION_CREATION_REQUEST.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

    @ExceptionHandler(InvalidEventReservationPaginationRequestException.class)
    public ResponseEntity<ServiceExceptionResponse> handleInvalidEventReservationPaginationRequestException(InvalidEventReservationPaginationRequestException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        INVALID_EVENT_RESERVATION_PAGINATION_REQUEST,
                        INVALID_EVENT_RESERVATION_PAGINATION_REQUEST.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

    @ExceptionHandler(InvalidCapacityException.class)
    public ResponseEntity<ServiceExceptionResponse> handleInvalidCapacityException(InvalidCapacityException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ServiceExceptionResponse(
                        INVALID_CAPACITY_REQUEST,
                        INVALID_CAPACITY_REQUEST.getMessage(),
                        List.of(exception.getMessageList()))
                );
    }

}
