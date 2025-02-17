package com.webculcate.event.reservation.service.core.controller.eventreservation;

import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationCreationRequest;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationCreationResponse;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationPaginationRequest;
import com.webculcate.event.reservation.service.core.model.dto.eventreservation.EventReservationPaginationResponse;
import com.webculcate.event.reservation.service.core.service.eventreservation.EventReservationServiceManager;
import com.webculcate.event.reservation.service.core.service.eventreservation.IEventReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/event/reservation/v1")
@RequiredArgsConstructor
public class EventReservationController {

    private final EventReservationServiceManager reservationServiceManager;

    @PostMapping
    public ResponseEntity<EventReservationCreationResponse> createNewEventReservation(@RequestBody EventReservationCreationRequest request) {
        IEventReservationService eventReservationService = reservationServiceManager.getEventReservationService();
        return new ResponseEntity<>(eventReservationService.createEventReservation(request), OK);
    }

    @GetMapping
    public ResponseEntity<EventReservationPaginationResponse> getEventReservationList(@RequestBody EventReservationPaginationRequest request) {
        IEventReservationService eventReservationService = reservationServiceManager.getEventReservationService();
        return new ResponseEntity<>(eventReservationService.getEventReservationList(request), OK);
    }

}
