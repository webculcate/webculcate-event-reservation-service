package com.webculcate.event.reservation.service.core.exception.eventreservation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class InvalidEventReservationPaginationRequestException extends RuntimeException {

    private List<String> messageList = new ArrayList<>();

    public InvalidEventReservationPaginationRequestException(List<String> errorMessageList) {
        this.messageList = errorMessageList;
    }
}
