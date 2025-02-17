package com.webculcate.event.reservation.service.core.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceExceptionType {

    INVALID_EVENT_RESERVATION_CREATION_REQUEST("Event reservation creation request invalid"),
    INVALID_CAPACITY_REQUEST("Capacity invalid");

    private final String message;

}
