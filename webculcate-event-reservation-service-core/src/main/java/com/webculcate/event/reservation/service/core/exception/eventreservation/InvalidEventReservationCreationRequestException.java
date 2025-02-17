package com.webculcate.event.reservation.service.core.exception.eventreservation;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidEventReservationCreationRequestException extends RuntimeException {

    private List<String> messageList;

    public InvalidEventReservationCreationRequestException(List<String> errorMessageList) {
        this.messageList = errorMessageList;
    }

}
