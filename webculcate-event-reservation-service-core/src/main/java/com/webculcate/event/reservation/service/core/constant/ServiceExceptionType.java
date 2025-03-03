package com.webculcate.event.reservation.service.core.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceExceptionType {

    INVALID_EVENT_RESERVATION_CREATION_REQUEST("Event reservation creation request invalid"),
    INVALID_EVENT_RESERVATION_PAGINATION_REQUEST("Event pagination creation request invalid"),
    EVENT_RESERVATION_CREATION_FAILED("Failed to create event reservation"),
    INVALID_CAPACITY_REQUEST("Capacity invalid");

    private final String message;

}
