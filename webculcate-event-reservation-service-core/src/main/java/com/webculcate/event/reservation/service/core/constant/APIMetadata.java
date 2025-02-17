package com.webculcate.event.reservation.service.core.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum APIMetadata {

    SCHEDULED_EVENT_RESERVATION("Event reservation created successfully", "Failed to create event reservation");

    private final String successMessage;

    private final String failureMessage;

}
