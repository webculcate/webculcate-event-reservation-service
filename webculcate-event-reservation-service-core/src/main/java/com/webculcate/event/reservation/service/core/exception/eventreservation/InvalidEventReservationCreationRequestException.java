package com.webculcate.event.reservation.service.core.exception.eventreservation;

import java.util.List;

public class InvalidEventReservationCreationRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidEventReservationCreationRequestException(List<String> errorMessageList) {
        this.messageList = errorMessageList;
    }

}
