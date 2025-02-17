package com.webculcate.event.reservation.service.core.exception.eventreservation;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InvalidCapacityException extends RuntimeException {

    private List<String> messageList;

    public InvalidCapacityException(List<String> messageList) {
        this.messageList = messageList;
    }

}
